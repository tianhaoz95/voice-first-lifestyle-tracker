package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun MainScreenTitle() {
    Text(
        text = "Nutrition Book",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(Dp(16.0F))
    )
}