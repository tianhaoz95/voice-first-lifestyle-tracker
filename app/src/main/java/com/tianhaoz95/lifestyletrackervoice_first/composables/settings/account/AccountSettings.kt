package com.tianhaoz95.lifestyletrackervoice_first.composables.settings.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun AccountSettingsTitle() {
    Text(
        text = "Account",
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(Dp(4.0F))
    )
}

@Composable
fun AccountSettings(onSignOut: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(Dp(4.0F)),
        elevation = Dp(2.0F)
    ) {
       Column(
           modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           AccountSettingsTitle()
           Divider()
           TextButton(onClick = { onSignOut() }) {
               Text(text = "Sign out")
           }
       }
    }
}