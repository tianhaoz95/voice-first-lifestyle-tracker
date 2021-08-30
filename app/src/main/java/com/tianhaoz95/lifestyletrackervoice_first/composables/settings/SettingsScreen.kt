package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.account.AccountSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.developer.DeveloperSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.linking.AccountLinkingSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.SettingsViewModel

@Composable
fun SettingsScreenContent(
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit,
    onIsDeveloperChange: (newValue: Boolean) -> Unit,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(Dp(4.0F)),
                color = MaterialTheme.colors.primary
            )
        }
        AccountLinkingSettings(
            linkGoogleFitHandler,
            unlinkGoogleFitHandler
        )
        DeveloperSettings(
            onShouldReportCrashChange,
            onIsDeveloperChange)
        AccountSettings(onSignOut)
    }
}

@Composable
fun SettingsScreen(
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit,
    onIsDeveloperChange: (newValue: Boolean) -> Unit,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit,
    onSignOut: () -> Unit,
) {
    AppTheme {
        SettingsScreenContent(
            onShouldReportCrashChange,
            onIsDeveloperChange,
            linkGoogleFitHandler,
            unlinkGoogleFitHandler,
            onSignOut
        )
    }
}