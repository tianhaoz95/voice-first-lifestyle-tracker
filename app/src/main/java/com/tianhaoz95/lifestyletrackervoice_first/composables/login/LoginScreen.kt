package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

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
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}

@Composable
fun LoginScreenSubtitle() {
    Text(
        text = "Stay on top of your nutrition intake",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(horizontal = Dp(16.0F))
    )
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSignIn: () -> Unit
) {
    val status: String by viewModel.status.observeAsState("")
    val details: String by viewModel.details.observeAsState("")

    AppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.0F),
                verticalAlignment = Alignment.Bottom
            ) {
                LoginScreenTitle()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0F),
                verticalAlignment = Alignment.Top
            ) {
                LoginScreenSubtitle()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LoginButton(onSignIn)
            }
            if (status.isNotEmpty() && details.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0F),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = status)
                    Text(text = details)
                }
            }
        }
    }
}