package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsViewModel

class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel = SettingsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchCrashReporting()
        setContent {
            SettingsScreen(
                viewModel = viewModel,
                onShouldReportCrashChange = {
                    updateCrashReporting(it)
                })
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
        with (sharedPref.edit()) {
            putBoolean("shouldReportCrash", update)
            apply()
        }
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(update)
    }
}