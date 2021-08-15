package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

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
        setContent {
            SettingsScreen(
                viewModel = viewModel,
                onShouldReportCrashChange = {
                    viewModel.updateShouldReportCrash(it)
                })
        }
    }
}