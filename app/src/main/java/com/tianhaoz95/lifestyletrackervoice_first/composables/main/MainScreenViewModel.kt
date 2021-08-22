package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    private val _isReady = MutableLiveData(false)
    val isReady: LiveData<Boolean> = _isReady

    fun updateIsReady(newValue: Boolean) {
        _isReady.value = newValue
    }
}
