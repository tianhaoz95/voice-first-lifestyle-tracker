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
fun MainScreenTitle() {
    Text(
        text = "Nutrition Book",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .padding(Dp(16.0F))
    )
}

@Composable
fun MainScreenMenuButton(label: String, handler: () -> Unit) {
    val topLeftCurveRadius: Float = 16.0F
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = Dp(0.0F),
                vertical = Dp(8.0F)
            )
    ) {
        Spacer(modifier = Modifier.width(Dp(36.0F)))
        OutlinedButton(
            onClick = { handler() },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = Dp(topLeftCurveRadius),
                topEnd = Dp(0.0F),
                bottomStart = Dp(0.0F),
                bottomEnd = Dp(0.0F),
            )
        ) {
            Text(text = label, style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun MainScreen(
    addRecordHandler: () -> Unit,
    reportsHandler: () -> Unit,
    settingsHandler: () -> Unit,
) {
    AppTheme(
        content = {
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
    )
}
