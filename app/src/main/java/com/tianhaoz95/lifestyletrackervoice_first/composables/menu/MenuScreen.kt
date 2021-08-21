package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.composables.theme.AppTheme
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun MenuRow(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
    Spacer(modifier = Modifier.height(Dp(16.0F)))
}

@Composable
fun MenuScreenContent(
    viewModel: MenuScreenViewModel,
    typeList: List<IntakeItemCategory>,
    unitList: List<IntakeItemUnit>,
    onAddHandler: () -> Unit,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MenuScreenTitle()
            MenuRow {
                Text(text = "Intake type:")
                IntakeTypeMenu(viewModel, typeList)
            }
            MenuRow {
                QuantityInput(viewModel)
                IntakeUnitMenu(viewModel, unitList)
            }
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
