package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private val _shouldReportCrash = MutableLiveData<Boolean>(false)
    val shouldReportCrash: LiveData<Boolean> = _shouldReportCrash

    fun updateShouldReportCrash(newValue: Boolean) {
        _shouldReportCrash.value = newValue
    }
}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onShouldReportCrashChange: (updatedValue: Boolean) -> Unit
) {
    val shouldReportCrash: Boolean by viewModel.shouldReportCrash.observeAsState(
        false
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Report crash")
            Switch(
                checked = shouldReportCrash,
                onCheckedChange = { onShouldReportCrashChange(it) })
        }
    }
}