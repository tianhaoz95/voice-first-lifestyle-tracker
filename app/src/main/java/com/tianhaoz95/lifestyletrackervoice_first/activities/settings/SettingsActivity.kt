package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.blocs.AuthenticationController
import com.tianhaoz95.lifestyletrackervoice_first.blocs.GoogleFitController
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.SettingsViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
import com.tianhaoz95.lifestyletrackervoice_first.utilities.LocalSettingsManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "SettingsActivity"
        private const val linkGoogleFitRequestCode: Int = 1
    }

    @Inject
    lateinit var googleFitService: GoogleFitService

    @Inject
    lateinit var userDataService: UserDataService

    @Inject
    lateinit var localSettingsManager: LocalSettingsManager

    @Inject
    lateinit var googleFitController: GoogleFitController

    @Inject
    lateinit var authenticationController: AuthenticationController

    private val model: SettingsViewModel by viewModels()

    /**
     * This is the deprecated way to observe the result of a returning activity,
     * but the GoogleSignIn still has this is the recommended approach. We will
     * need to wait for the package to migration to registerForActivityResult
     * before we can migrate our code.
     *
     * For details, see:
     * https://developers.google.com/android/reference/com/google/android/gms/auth/api/signin/GoogleSignIn
     */
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
                        Log.i(TAG, "Fit Link success.")
                        model.updateIsGoogleFitLinked(
                            googleFitService.isLinked(this)
                        )
                    }
                    else -> Log.e(TAG, "Fit Link error: $resultCode")
                }
            }
            else -> {
                Log.e(TAG, "Invalid request code: $requestCode")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refreshSettings()
        setContent {
            SettingsScreen(
                onShouldReportCrashChange = {
                    updateCrashReporting(it)
                },
                onIsDeveloperChange = {
                    updateIsDeveloper(it)
                },
                linkGoogleFitHandler = {
                    googleFitController.link()

                },
                unlinkGoogleFitHandler = {
                    googleFitController.unlink(onUnlinkDone = { hasError, msg ->
                        Log.i(
                            TAG,
                            "Link Fit status: $hasError, message: $msg"
                        )
                        refreshGoogleFitStatus()
                    })
                },
                onSignOut = { authenticationController.signOut() }
            )
        }
    }

    private fun refreshSettings() {
        model.updateIsDeveloper(
            localSettingsManager.getIsDeveloper()
        )
        model.updateShouldReportCrash(
            localSettingsManager.getShouldReportCrash()
        )
        refreshGoogleFitStatus()
    }

    private fun refreshGoogleFitStatus() {
        model.updateIsGoogleFitLinked(googleFitService.isLinked(this))
    }

    private fun updateCrashReporting(update: Boolean) {
        userDataService.updateShouldReportCrash(update)
        localSettingsManager.setShouldReportCrash(update)
        model.updateShouldReportCrash(update)
    }

    private fun updateIsDeveloper(update: Boolean) {
        userDataService.updateIsDeveloper(update)
        localSettingsManager.setIsDeveloper(update)
        model.updateIsDeveloper(update)
    }
}