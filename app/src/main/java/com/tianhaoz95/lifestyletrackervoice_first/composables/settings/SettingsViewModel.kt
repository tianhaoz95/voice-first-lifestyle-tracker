package com.tianhaoz95.lifestyletrackervoice_first.composables.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private val _shouldReportCrash = MutableLiveData(false)
    val shouldReportCrash: LiveData<Boolean> = _shouldReportCrash

    private val _isGoogleFitLinked = MutableLiveData(false)
    val isGoogleFitLinked: LiveData<Boolean> = _isGoogleFitLinked

    fun updateIsGoogleFitLinked(newValue: Boolean) {
        _isGoogleFitLinked.value = newValue
    }

    fun updateShouldReportCrash(newValue: Boolean) {
        _shouldReportCrash.value = newValue
    }
}