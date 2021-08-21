package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun MenuScreenContent(
    viewModel: MenuScreenViewModel,
    typeList: List<IntakeItemCategory>,
    unitList: List<IntakeItemUnit>,
    onAddHandler: () -> Unit,
) {
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
            Row(
                modifier = Modifier.weight(2.0F),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Intake type:",
                    modifier = Modifier.padding(Dp(8.0F)),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary
                )
            }
            Column(modifier = Modifier.weight(1.0F)) {
                IntakeTypeMenu(viewModel, typeList)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(2.0F)
            ) {
                QuantityInput(viewModel)
            }
            Column(
                modifier = Modifier
                    .weight(1.0F)
            ) {
                IntakeUnitMenu(viewModel, unitList)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuScreenSubmission(viewModel, onAddHandler)
        }
    }
}

@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel,
    typeList: List<IntakeItemCategory>,
    unitList: List<IntakeItemUnit>,
    onAddHandler: () -> Unit,
) {
    AppTheme {
        MenuScreenContent(
            viewModel,
            typeList,
            unitList,
            onAddHandler,
        )
    }
}
