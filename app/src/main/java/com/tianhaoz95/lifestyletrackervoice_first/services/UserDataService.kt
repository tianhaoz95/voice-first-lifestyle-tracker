package com.tianhaoz95.lifestyletrackervoice_first.services

import android.app.Activity.RESULT_CANCELED
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationReport
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataService @Inject constructor() {
    private var db: FirebaseFirestore = Firebase.firestore
    private var user: FirebaseUser? = Firebase.auth.currentUser
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics
    private var remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private var shouldReportCrash: Boolean = true
    private var isDeveloper: Boolean = false
    var records: MutableList<HydrationRecord> = mutableListOf()

    val recordCount get() = records.size
    val currentDaySummary get() = HydrationReport(records).toCurrentDaySummary()

    fun initialize(
        context: Context,
        getIsDeveloper: () -> Boolean?,
    ) {
        maybeNeedAuthentication(context)
        initializeDeveloperIdentifier(getIsDeveloper)
        initializeRemoteConfig()
    }

    fun isAuthenticated(): Boolean {
        user = Firebase.auth.currentUser
        return user != null
    }

    private fun initializeDeveloperIdentifier(getIsDeveloper: () -> Boolean?) {
        val isDebugBuild: Boolean = BuildConfig.DEBUG
        isDeveloper = getIsDeveloper() ?: isDebugBuild
        firebaseAnalytics.setUserProperty(
            "Developer",
            isDeveloper.toString()
        )
    }

    fun updateIsDeveloper(newValue: Boolean) {
        isDeveloper = newValue
        firebaseAnalytics.setUserProperty(
            "Developer",
            newValue.toString()
        )
    }

    private fun initializeRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("show_report_in_menu" to false))
        remoteConfig.fetchAndActivate()
    }

    val isReportEnabled: Boolean get() = remoteConfig.getBoolean(
        "show_report_in_menu")

    fun maybeNeedAuthentication(context: Context): Unit {
        user = Firebase.auth.currentUser
        if (user == null || user!!.uid == null) {
            val intent = Intent(context, AuthenticationActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun getSignInIntent(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
        return AuthUI
            .getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    fun signOut(context: Context, onComplete: () -> Unit) {
        AuthUI
            .getInstance()
            .signOut(context)
            .addOnCompleteListener {
                val intent = Intent(
                    context, AuthenticationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(intent)
                onComplete()
            }
    }

    fun addHydrationRecord(
        context: Context,
        record: HydrationRecord,
        onError: (code: Int, msg: String) -> Unit,
        onSuccess: () -> Unit
    ) {
        maybeNeedAuthentication(context)
        val dataRef: CollectionReference = getUserTrackingDataRef(user?.uid!!)
        dataRef.add(record.toDatabaseEntry())
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onError(RESULT_CANCELED, e.message.orEmpty())
            }
    }

    fun getCurrentDayHydration(
        context: Context,
        onError: (code: Int, msg: String) -> Unit,
        onSuccess: () -> Unit,
    ) {
        maybeNeedAuthentication(context)
        val dataRef: CollectionReference = getUserTrackingDataRef(user?.uid!!)
        val timeWindowDataRef = dataRef
            .whereGreaterThan("timestamp", getCurrentDayStartTimestamp())
            .whereLessThan("timestamp", getCurrentDayEndTimestamp())
        timeWindowDataRef
            .get()
            .addOnSuccessListener { snapshot ->
                records.clear()
                snapshot.documents.forEach { document ->
                    val type = IntakeItemCategory.valueOf(
                        document.data?.get("type") as String
                    )
                    val quantity = document
                        .data?.get("quantity")
                        .toString()
                        .toInt()
                    val unit = IntakeItemUnit.valueOf(
                        document.data?.get("unit") as String
                    )
                    val timestamp = document
                        .data?.get("timestamp")
                        .toString()
                        .toLong()
                    records.add(HydrationRecord(type, quantity, unit, timestamp))
                }
                onSuccess()
            }
            .addOnFailureListener { e ->
                onError(RESULT_CANCELED, e.message.orEmpty())
            }
    }

    private fun getUserTrackingDataRef(uid: String): CollectionReference {
        return db
            .collection("user_data")
            .document(uid)
            .collection("tracking_data")
    }

    private fun getCurrentDayStartTimestamp(): Long {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, 0)
        currentTime.set(Calendar.MINUTE, 0)
        currentTime.set(Calendar.SECOND, 0)
        return currentTime.timeInMillis
    }

    private fun getCurrentDayEndTimestamp(): Long {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, 23)
        currentTime.set(Calendar.MINUTE, 59)
        currentTime.set(Calendar.SECOND, 59)
        return currentTime.timeInMillis
    }
}