package com.tianhaoz95.lifestyletrackervoice_first.composables.record

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

class AddRecordViewModel : ViewModel() {
    private val _status = MutableLiveData<String>("")
    val status: LiveData<String> = _status

    private val _details = MutableLiveData<String>("")
    val details: LiveData<String> = _details

    fun setNewStatus(newStatus: String, newDetails: String) {
        _status.value = newStatus
        _details.value = newDetails
    }
}

@Composable
fun AddRecordScreenContent(viewModel: AddRecordViewModel) {
    val status: String by viewModel.status.observeAsState("")
    val details: String by viewModel.details.observeAsState("")

    Column() {
        Text(text = status)
        Text(text = details)
    }
}

@Composable
fun AddRecordScreen(viewModel: AddRecordViewModel) {
    AppTheme {
        AddRecordScreenContent(viewModel)
    }
}