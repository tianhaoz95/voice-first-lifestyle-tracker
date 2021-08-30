package com.tianhaoz95.lifestyletrackervoice_first.activities.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.menu.MenuActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.report.ShowReportActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.settings.SettingsActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.main.MainScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.MainScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.utilities.EntrypointInitializer
import com.tianhaoz95.lifestyletrackervoice_first.utilities.LocalSettingsManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "MainActivity"
    }

    @Inject
    lateinit var userDataService: UserDataService

    @Inject
    lateinit var entrypointInitializer: EntrypointInitializer

    @Inject
    lateinit var localSettingsManager: LocalSettingsManager
    private val model: MainScreenViewModel by viewModels()
    private val authenticateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { initializeData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entrypointInitializer.initialize(
            authenticateLauncher
        ) { initializeData() }
        setContent {
            MainScreen(
                viewModel = model,
                addRecordHandler = { addRecordHandler() },
                reportsHandler = { reportsHandler() },
                settingsHandler = { settingsHandler() },
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        maybeLaunchFeature(intent)
    }

    private fun initializeData() {
        userDataService.initialize(
            getIsDeveloper = { localSettingsManager.getIsDeveloper() }
        )
        maybeLaunchFeature(intent)
        model.updateIsReady(true)
        model.updateShowReport(userDataService.isReportEnabled)
    }

    // TODO: refactor this into a BLOC helper, e.g., featureLaunchController
    private fun maybeLaunchFeature(intent: Intent?) {
        if (
            intent == null ||
            intent.extras == null ||
            intent.extras?.get("featureParam") == null
        ) {
            Log.i(TAG, "featureParam not found in intent, skip.")
            return
        }
        val featureId: String = intent.extras?.get("featureParam") as String
        Log.i(TAG, "Found featureId $featureId")
        when (featureId) {
            "RECORD" -> addRecordHandler()
            "SETTINGS" -> settingsHandler()
            "REPORT" -> reportsHandler()
            else -> {
                userDataService.addRemoteCrashLog(
                    "$featureId is not a valid feature ID.",
                    TAG
                )
            }
        }
    }

    private fun addRecordHandler() {
        startActivity(Intent(this, MenuActivity::class.java))
    }

    private fun reportsHandler() {
        startActivity(Intent(this, ShowReportActivity::class.java))
    }

    private fun settingsHandler() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}