package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input.MenuScreenInput
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
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
            MenuScreenInput(viewModel, typeList, unitList)
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
