package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _status = MutableLiveData<String>("")
    val status: LiveData<String> = _status

    private val _details = MutableLiveData<String>("")
    val details: LiveData<String> = _details

    fun setNewStatus(newStatus: String, newDetails: String) {
        _status.value = newStatus
        _details.value = newDetails
    }
}