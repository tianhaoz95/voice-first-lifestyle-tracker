package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

@Composable
fun MainScreenContent(
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MainScreenTitle()
        Spacer(modifier = Modifier.height(Dp(16.0F)))
        MainScreenMenuButton(
            label = "Add",
            handler = { addRecordHandler() })
        MainScreenMenuButton(
            label = "Reports",
            handler = { reportsHandler() })
        MainScreenMenuButton(
            label = "Settings",
            handler = { settingsHandler() })
    }
}

@Composable
fun MainScreen(
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
) {
    AppTheme {
        MainScreenContent(
            addRecordHandler,
            reportsHandler,
            settingsHandler
        )
    }
}
