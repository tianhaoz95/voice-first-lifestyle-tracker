package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.developer.ReportCrashSetting

@Composable
fun DeveloperSettingsTitle() {
    Text(
        text = "Developer Settings",
        modifier = Modifier.padding(Dp(4.0F))
    )
}

@Composable
fun DeveloperSettings(
    viewModel: SettingsViewModel,
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit
) {
    Card(
        modifier = Modifier.padding(Dp(4.0F)),
        elevation = Dp(2.0F)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DeveloperSettingsTitle()
            ReportCrashSetting(viewModel, onShouldReportCrashChange)
        }
    }
}