package com.tianhaoz95.lifestyletrackervoice_first.composables.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme

@Composable
fun MainScreenMenuButton(label: String, handler: () -> Unit) {
    Button(
        onClick = { handler() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(10.0F))
    ) {
        Text(text = label)
    }
}

@Composable
fun MainScreen(
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
    signOutHandler: () -> Unit,
) {
    AppTheme(
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MainScreenMenuButton(
                    label = "Add",
                    handler = { addRecordHandler() })
                MainScreenMenuButton(
                    label = "Reports",
                    handler = { reportsHandler() })
                MainScreenMenuButton(
                    label = "Settings",
                    handler = { settingsHandler() })
                MainScreenMenuButton(
                    label = "Sign out",
                    handler = { signOutHandler() })
            }
        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        addRecordHandler = {},
        reportsHandler = {},
        settingsHandler = {},
        signOutHandler = {},
    )
}