package com.tianhaoz95.lifestyletrackervoice_first.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _details = MutableLiveData("")
    val details: LiveData<String> = _details

    fun setNewStatus(newStatus: String, newDetails: String) {
        _status.value = newStatus
        _details.value = newDetails
    }
}