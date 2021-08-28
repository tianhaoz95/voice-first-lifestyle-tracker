package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel

@Composable
fun MenuScreenSubmission(
    viewModel: MenuScreenViewModel,
    onAddHandler: () -> Unit,
) {
    val canSubmit: Boolean by viewModel
        .canSubmit.observeAsState(true)
    val topLeftCurveRadius: Float = 16.0F

    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1.0F))
        OutlinedButton(
            onClick = { onAddHandler() },
            modifier = Modifier
                .fillMaxWidth()
                .weight(3.0F),
            shape = RoundedCornerShape(
                topStart = Dp(topLeftCurveRadius),
                topEnd = Dp(0.0F),
                bottomStart = Dp(0.0F),
                bottomEnd = Dp(0.0F),
            ),
            enabled = canSubmit,
        ) {
            Text(
                text = "Add",
                style = MaterialTheme.typography.h6
            )
        }
    }
}