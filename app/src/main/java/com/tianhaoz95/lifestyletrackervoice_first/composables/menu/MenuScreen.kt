package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input.MenuScreenInput
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun MenuScreenContent(onAddHandler: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuScreenTitle()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuScreenInput()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuScreenSubmission(onAddHandler)
        }
    }
}

@Composable
fun MenuScreen(onAddHandler: () -> Unit) {
    AppTheme {
        MenuScreenContent(onAddHandler)
    }
}
