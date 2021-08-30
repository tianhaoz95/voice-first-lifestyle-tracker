package com.tianhaoz95.lifestyletrackervoice_first.composables.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.LoginScreenModel

@Composable
fun LoginScreenContent(
    onSignIn: () -> Unit,
    model: LoginScreenModel = viewModel()
) {
    val status: String by model.status.observeAsState("")
    val details: String by model.details.observeAsState("")

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
            if (status.isNotEmpty() && details.isNotEmpty()) {
                Text(text = status)
                Text(text = details)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoginButton(onSignIn)
        }
    }
}

@Composable
fun LoginScreen(onSignIn: () -> Unit) {
    AppTheme {
        LoginScreenContent(onSignIn)
    }
}