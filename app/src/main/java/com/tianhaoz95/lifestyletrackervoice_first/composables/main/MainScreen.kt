package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.MainScreenViewModel

@Composable
fun MainScreenContent(
    viewModel: MainScreenViewModel,
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
) {
    val isReady: Boolean by viewModel
        .isReady.observeAsState(false)
    val showReport: Boolean by viewModel
        .showReport.observeAsState(false)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MainScreenTitle()
        Spacer(modifier = Modifier.height(Dp(16.0F)))
        if (isReady) {
            MainScreenMenuButton(
                label = "Add",
                handler = { addRecordHandler() })
            if (showReport) {
                MainScreenMenuButton(
                    label = "Reports",
                    handler = { reportsHandler() })
            }
            MainScreenMenuButton(
                label = "Settings",
                handler = { settingsHandler() })
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
) {
    AppTheme {
        MainScreenContent(
            viewModel,
            addRecordHandler,
            reportsHandler,
            settingsHandler
        )
    }
}
