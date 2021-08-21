package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.account.AccountSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.developer.DeveloperSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.linking.AccountLinkingSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

@Composable
fun SettingsScreenContent(
    viewModel: SettingsViewModel,
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit,
    onSignOut: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(Dp(4.0F))
        )
        AccountLinkingSettings(
            viewModel,
            linkGoogleFitHandler,
            unlinkGoogleFitHandler
        )
        DeveloperSettings(viewModel, onShouldReportCrashChange)
        AccountSettings(onSignOut)
    }
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit,
    onSignOut: () -> Unit,
) {
    AppTheme {
        SettingsScreenContent(
            viewModel,
            onShouldReportCrashChange,
            linkGoogleFitHandler,
            unlinkGoogleFitHandler,
            onSignOut
        )
    }
}