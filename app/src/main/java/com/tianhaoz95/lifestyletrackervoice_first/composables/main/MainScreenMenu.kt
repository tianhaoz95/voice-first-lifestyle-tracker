package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun MainScreenMenuButton(label: String, handler: () -> Unit) {
    val topLeftCurveRadius: Float = 16.0F
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dp(0.0F),
                vertical = Dp(8.0F)
            )
    ) {
        Spacer(modifier = Modifier.width(Dp(36.0F)))
        OutlinedButton(
            onClick = { handler() },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = Dp(topLeftCurveRadius),
                topEnd = Dp(0.0F),
                bottomStart = Dp(0.0F),
                bottomEnd = Dp(0.0F),
            )
        ) {
            Text(text = label, style = MaterialTheme.typography.h6)
        }
    }
}