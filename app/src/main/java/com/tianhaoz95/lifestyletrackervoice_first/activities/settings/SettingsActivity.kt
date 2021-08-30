package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
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
        model.updateIsDeveloper(localSettingsManager.getIsDeveloper())
        model.updateShouldReportCrash(
            localSettingsManager.getShouldReportCrash()
        )
        model.updateIsGoogleFitLinked(
            googleFitService.isLinked(this)
        )
        setContent {
            SettingsScreen(
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

    private fun updateCrashReporting(update: Boolean) {
        model.updateShouldReportCrash(update)
        userDataService.updateShouldReportCrash(update)
        localSettingsManager.setShouldReportCrash(update)
    }

    private fun updateIsDeveloper(update: Boolean) {
        userDataService.updateIsDeveloper(update)
        model.updateIsDeveloper(update)
        localSettingsManager.setIsDeveloper(update)
    }
}