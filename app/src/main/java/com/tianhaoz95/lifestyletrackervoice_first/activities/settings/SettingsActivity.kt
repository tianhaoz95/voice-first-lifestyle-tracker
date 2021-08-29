package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.SettingsViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    @Inject
    lateinit var googleFitService: GoogleFitService
    @Inject
    lateinit var userDataService: UserDataService
    private val model: SettingsViewModel by viewModels()
    private val linkGoogleFitRequestCode: Int = 1

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            linkGoogleFitRequestCode -> {
                when (resultCode) {
                    RESULT_OK -> {
                        println("tianhaoz_debug: link success")
                        model.updateIsGoogleFitLinked(
                            googleFitService.isLinked(this)
                        )
                    }
                    else -> println("tianhaoz_debug: link error $requestCode $resultCode")
                }
            }
            else -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchCrashReporting()
        fetchIsDeveloper()
        model.updateIsGoogleFitLinked(
            googleFitService.isLinked(this)
        )
        setContent {
            SettingsScreen(
                viewModel = model,
                onShouldReportCrashChange = {
                    updateCrashReporting(it)
                },
                onIsDeveloperChange = {
                    updateIsDeveloper(it)
                },
                linkGoogleFitHandler = { linkFit() },
                unlinkGoogleFitHandler = { unlinkFit() },
                onSignOut = { onSignOut() }
            )
        }
    }

    private fun unlinkFit() {
        Fitness.getConfigClient(
            this, googleFitService.getAccount(this)
        )
            .disableFit()
            .addOnSuccessListener {
                GoogleSignIn
                    .getClient(this, googleFitService.signInOptions)
                    .revokeAccess()
                model.updateIsGoogleFitLinked(
                    googleFitService.isLinked(this)
                )
            }
            .addOnFailureListener { e ->
                model.updateIsGoogleFitLinked(
                    googleFitService.isLinked(this)
                )
            }
    }

    private fun linkFit() {
        val account = googleFitService.getAccount(this)
        if (!googleFitService.isLinked(this)) {
            GoogleSignIn.requestPermissions(
                this,
                linkGoogleFitRequestCode,
                account,
                googleFitService.options
            )
        }
    }

    private fun onSignOut() {
        userDataService.signOut(this) {
            finish()
        }
    }

    private fun fetchCrashReporting() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val shouldReportCrash = sharedPref.getBoolean("shouldReportCrash", true)
        model.updateShouldReportCrash(shouldReportCrash)
    }

    private fun updateCrashReporting(update: Boolean) {
        model.updateShouldReportCrash(update)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("shouldReportCrash", update)
            apply()
        }
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(update)
    }

    private fun fetchIsDeveloper() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val shouldReportCrash = sharedPref.getBoolean("shouldReportCrash", true)
        model.updateShouldReportCrash(shouldReportCrash)
    }

    private fun updateIsDeveloper(update: Boolean) {
        userDataService.updateIsDeveloper(update)
        model.updateIsDeveloper(update)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("shouldReportCrash", update)
            apply()
        }
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(update)
    }
}