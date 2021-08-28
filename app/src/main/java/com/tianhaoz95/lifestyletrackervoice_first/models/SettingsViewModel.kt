package com.tianhaoz95.lifestyletrackervoice_first.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
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

    private val _isDeveloper = MutableLiveData(false)
    val isDeveloper: LiveData<Boolean> = _isDeveloper

    fun updateIsDeveloper(newValue: Boolean) {
        _isDeveloper.value = newValue
    }
}