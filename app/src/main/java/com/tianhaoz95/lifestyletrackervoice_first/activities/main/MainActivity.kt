package com.tianhaoz95.lifestyletrackervoice_first.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tianhaoz95.lifestyletrackervoice_first.activities.menu.MenuActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.report.ShowReportActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.settings.SettingsActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.main.MainScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.MainScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var userDataService: UserDataService
    private val model: MainScreenViewModel by viewModels()
    private val tag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDataService.initialize(
            context = this,
            getIsDeveloper = { getDeveloperIdentity() }
        )
        maybeLaunchFeature(intent)
        model.updateIsReady(true)
        model.updateShowReport(userDataService.isReportEnabled)
        setContent {
            MainScreen(
                viewModel = model,
                addRecordHandler = { addRecordHandler() },
                reportsHandler = { reportsHandler() },
                settingsHandler = { settingsHandler() },
            )
        }
    }

    private fun getDeveloperIdentity(): Boolean? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return if (sharedPref.contains("isDeveloper")) {
            sharedPref.getBoolean("isDeveloper", true)
        } else {
            null
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        maybeLaunchFeature(intent)
    }

    private fun maybeLaunchFeature(intent: Intent?) {
        if (
            intent == null ||
            intent.extras == null ||
            intent.extras?.get("featureParam") == null
        ) {
            Log.i(tag, "featureParam not found in intent, skip.")
            return
        }
        val featureId: String = intent.extras?.get("featureParam") as String
        Log.i(tag, "Found featureId $featureId")
        when (featureId) {
            "RECORD" -> addRecordHandler()
            "SETTINGS" -> settingsHandler()
            "REPORT" -> reportsHandler()
            else -> {
                val msg: String = "$featureId is not a valid feature ID."
                Log.e(tag, msg)
                logFeatureLaunchFailure(msg)
            }
        }
    }

    private fun logFeatureLaunchFailure(msg: String) {
        FirebaseCrashlytics
            .getInstance()
            .log(msg)
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