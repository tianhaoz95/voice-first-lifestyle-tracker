package com.tianhaoz95.lifestyletrackervoice_first.composables.settings.linking

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsViewModel

@Composable
fun AccountLinkingSettingsTitle() {
    Text(text = "Link Accounts", modifier = Modifier.padding(Dp(4.0F)))
}

@Composable
fun LinkGoogleFitSection(
    viewModel: SettingsViewModel,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit,
) {
    val isGoogleFitLinked: Boolean by viewModel
        .isGoogleFitLinked.observeAsState(false)

    Row(
        modifier = Modifier
            .padding(Dp(4.0F))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isGoogleFitLinked) {
            Text(text = "Google Fit is linked")
            TextButton(onClick = { unlinkGoogleFitHandler() }) {
                Text(text = "Unlink")
            }
        } else {
            Text(text = "Google Fit is not linked")
            TextButton(onClick = { linkGoogleFitHandler() }) {
                Text(text = "Link")
            }
        }
    }
}

@Composable
fun AccountLinkingSettings(
    viewModel: SettingsViewModel,
    linkGoogleFitHandler: () -> Unit,
    unlinkGoogleFitHandler: () -> Unit
) {
    val isGoogleFitLinked: Boolean by viewModel
        .isGoogleFitLinked.observeAsState(false)

    Card(
        modifier = Modifier.padding(Dp(4.0F)),
        elevation = Dp(2.0F)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountLinkingSettingsTitle()
            LinkGoogleFitSection(
                viewModel,
                linkGoogleFitHandler,
                unlinkGoogleFitHandler
            )
        }
    }
}