package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.LoginScreenModel

@Composable
fun LoginButton(
    onSignIn: () -> Unit
) {
    val topLeftCurveRadius: Float = 16.0F
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1.0F))
        OutlinedButton(
            onClick = { onSignIn() },
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.0F),
            shape = RoundedCornerShape(
                topStart = Dp(topLeftCurveRadius),
                topEnd = Dp(0.0F),
                bottomStart = Dp(0.0F),
                bottomEnd = Dp(0.0F),
            )
        ) {
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.h6
            )
        }
    }
}