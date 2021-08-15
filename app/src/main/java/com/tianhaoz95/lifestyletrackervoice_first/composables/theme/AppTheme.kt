package com.tianhaoz95.lifestyletrackervoice_first.composables.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        content = content,
        colors = if (darkTheme) DarkColors else LightColors
    )
}