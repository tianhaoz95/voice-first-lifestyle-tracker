package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.GoogleFitService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    @Inject
    lateinit var googleFitService: GoogleFitService
    private val viewModel: SettingsViewModel = SettingsViewModel()
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
                        viewModel.updateIsGoogleFitLinked(
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

    private fun unlinkFit() {
        Fitness.getConfigClient(
            this, googleFitService.getAccount(this)
        )
            .disableFit()
            .addOnSuccessListener {
                GoogleSignIn
                    .getClient(this, googleFitService.signInOptions)
                    .revokeAccess()
                viewModel.updateIsGoogleFitLinked(
                    googleFitService.isLinked(this)
                )
            }
            .addOnFailureListener { e ->
                viewModel.updateIsGoogleFitLinked(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchCrashReporting()
        viewModel.updateIsGoogleFitLinked(
            googleFitService.isLinked(this)
        )
        setContent {
            SettingsScreen(
                viewModel = viewModel,
                onShouldReportCrashChange = {
                    updateCrashReporting(it)
                },
                linkGoogleFitHandler = { linkFit() },
                unlinkGoogleFitHandler = { unlinkFit() }
            )
        }
    }

    private fun fetchCrashReporting() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val shouldReportCrash = sharedPref.getBoolean("shouldReportCrash", true)
        viewModel.updateShouldReportCrash(shouldReportCrash)
    }

    private fun updateCrashReporting(update: Boolean) {
        viewModel.updateShouldReportCrash(update)
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