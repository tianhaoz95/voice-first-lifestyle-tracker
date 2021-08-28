package com.tianhaoz95.lifestyletrackervoice_first.models

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataService: UserDataService
) : ViewModel() {
    private lateinit var _launcher: ActivityResultLauncher<Intent>

    private val _status = MutableLiveData("")
    val status: LiveData<String> = _status

    private val _details = MutableLiveData("")
    val details: LiveData<String> = _details

    fun setNewStatus(newStatus: String, newDetails: String) {
        _status.value = newStatus
        _details.value = newDetails
    }

    private fun clearStatus() {
        _status.value = ""
        _details.value = ""
    }

    fun setLauncher(launcher: ActivityResultLauncher<Intent>) {
        _launcher = launcher
    }

    fun launchSignIn() {
        clearStatus()
        _launcher.launch(userDataService.getSignInIntent())
    }
}