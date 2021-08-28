package com.tianhaoz95.lifestyletrackervoice_first.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    private val _isReady = MutableLiveData(false)
    val isReady: LiveData<Boolean> = _isReady

    fun updateIsReady(newValue: Boolean) {
        _isReady.value = newValue
    }

    private val _showReport = MutableLiveData(false)
    val showReport: LiveData<Boolean> = _showReport

    fun updateShowReport(newValue: Boolean) {
        _showReport.value = newValue
    }
}
