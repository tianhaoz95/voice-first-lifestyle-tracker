package com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun MenuScreenInput() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Intake type:",
                modifier = Modifier
                    .padding(Dp(8.0F))
                    .weight(1.0F),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary
            )
            Column(modifier = Modifier.weight(1.0F)) {
                IntakeTypeMenu()
            }
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1.0F),
                horizontalAlignment = Alignment.Start
            ) {
                QuantityInput()
            }
            Column(
                modifier = Modifier
                    .weight(1.0F),
                horizontalAlignment = Alignment.Start
            ) {
                IntakeUnitMenu()
            }
        }
    }
}