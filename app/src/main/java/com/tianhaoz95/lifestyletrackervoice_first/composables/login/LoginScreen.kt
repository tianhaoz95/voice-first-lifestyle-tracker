package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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

@Composable
fun LoginScreenTitle() {
    Text(
        text = "Nutrition Book",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}

@Composable
fun LoginScreenSubtitle() {
    Text(
        text = "Welcome :)",
        style = MaterialTheme.typography.h2,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}

@Composable
fun LoginScreen(viewModel: LoginViewModel, onSignIn: () -> Unit) {
    val status: String by viewModel.status.observeAsState("")
    val details: String by viewModel.details.observeAsState("")

    MaterialTheme() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            LoginScreenTitle()
            LoginScreenSubtitle()
            if (!status.isNullOrEmpty() && !details.isNullOrEmpty()) {
                Text(text = status)
                Text(text = details)
            }
            Spacer(modifier = Modifier.height(Dp(value = 32.0F)))
            OutlinedButton(
                onClick = { onSignIn() },
                modifier = Modifier
                    .padding(horizontal = Dp(16.0F))
                    .fillMaxWidth()
            ) {
                Text(text = "Sign in")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(viewModel = LoginViewModel(), onSignIn = {})
}