package com.tianhaoz95.lifestyletrackervoice_first.composables.record

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.AddRecordViewModel

@Composable
fun AddRecordScreenContent(model: AddRecordViewModel = viewModel()) {
    val status: String by model.status.observeAsState("")
    val details: String by model.details.observeAsState("")

    Column() {
        Text(text = status)
        Text(text = details)
    }
}

@Composable
fun AddRecordScreen() {
    AppTheme {
        AddRecordScreenContent()
    }
}