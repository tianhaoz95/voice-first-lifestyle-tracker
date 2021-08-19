package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.linking.AccountLinkingSettings
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit
) {
    AppTheme(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(Dp(4.0F))
                )
                AccountLinkingSettings(viewModel, linkGoogleFitHandler, unlinkGoogleFitHandler)
                DeveloperSettings(viewModel, onShouldReportCrashChange)
            }
        }
    )
}