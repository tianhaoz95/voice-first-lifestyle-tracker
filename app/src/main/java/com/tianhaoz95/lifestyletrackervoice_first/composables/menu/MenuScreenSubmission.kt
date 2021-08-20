package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun MenuScreenSubmission(
    viewModel: MenuScreenViewModel,
    onAddHandler: () -> Unit,
) {
    val canSubmit: Boolean by viewModel
        .canSubmit.observeAsState(true)

    OutlinedButton(
        onClick = { onAddHandler() },
        modifier = Modifier.fillMaxWidth(),
        enabled = canSubmit
    ) {
        Text(text = "Add")
    }
}