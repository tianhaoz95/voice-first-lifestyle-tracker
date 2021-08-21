package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun LoginScreenTitle() {
    Text(
        text = "Nutrition Book",
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}

@Composable
fun LoginScreenSubtitle() {
    Text(
        text = "Stay on top of your nutrition intake",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}