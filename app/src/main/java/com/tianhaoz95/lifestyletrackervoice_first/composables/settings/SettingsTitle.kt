package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SettingsTitle() {
    Text(
        text = "Report crash",
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(Dp(4.0F))
    )
}