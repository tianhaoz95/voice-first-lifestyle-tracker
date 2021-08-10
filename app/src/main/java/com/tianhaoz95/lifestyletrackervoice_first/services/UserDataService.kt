package com.tianhaoz95.lifestyletrackervoice_first.services

import android.app.Activity.RESULT_CANCELED
import android.content.Context
import android.content.Intent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tianhaoz95.lifestyletrackervoice_first.R
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit
import java.util.*
import javax.inject.Inject

class UserDataService @Inject constructor() {
    private var db: FirebaseFirestore = Firebase.firestore
    private var user: FirebaseUser? = Firebase.auth.currentUser

    fun maybeNeedAuthentication(context: Context): Unit {
        user = Firebase.auth.currentUser
        if (user == null || user!!.uid == null) {
            val intent = Intent(context, AuthenticationActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun getSignInIntent(): Intent {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        return AuthUI
            .getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
    }

    fun signOut(context: Context): Unit {
        Firebase.auth.signOut()
        val intent = Intent(context, AuthenticationActivity::class.java)
        context.startActivity(intent)
    }

    fun addHydrationRecord(
        context: Context,
        record: HydrationRecord,
        onError: (code: Int, msg: String) -> Unit,
        onSuccess: () -> Unit
    ): Unit {
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

    fun getCurrentDayHydration(context: Context): List<HydrationRecord> {
        maybeNeedAuthentication(context)
        val dataRef: CollectionReference = getUserTrackingDataRef(user?.uid!!)
        val timeWindowDataRef = dataRef
            .whereGreaterThan("timestamp", getCurrentDayStartTimestamp())
            .whereLessThan("timestamp", getCurrentDayEndTimestamp())
        val trackingData = timeWindowDataRef.get().result?.documents.orEmpty()
        trackingData.forEach { document ->
            val type = IntakeItemCategory.valueOf(
                document.data?.get("type") as String)
            val quantity = document.data?.get("quantity") as Int
            val unit = IntakeItemUnit.valueOf(
                document.data?.get("unit") as String)
            val timestamp = document.data?.get("timestamp") as Long
        }
        return listOf()
    }

    private fun getUserTrackingDataRef(uid: String): CollectionReference {
        return db
            .collection("user_data")
            .document(uid!!)
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