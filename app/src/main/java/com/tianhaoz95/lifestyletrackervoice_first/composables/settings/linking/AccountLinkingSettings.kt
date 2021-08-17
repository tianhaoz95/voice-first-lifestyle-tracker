package com.tianhaoz95.lifestyletrackervoice_first.composables.settings.linking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun AccountLinkingSettingsTitle() {
    Text(text = "Link Accounts")
}

@Composable
fun AccountLinkingSettings(linkGoogleFitHandler: () -> Unit) {
    Card(
        modifier = Modifier.padding(Dp(4.0F)),
        elevation = Dp(2.0F)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AccountLinkingSettingsTitle()
            Button(onClick = { linkGoogleFitHandler() }) {
                Text(text = "Link Google Fit")
            }
        }
    }
}