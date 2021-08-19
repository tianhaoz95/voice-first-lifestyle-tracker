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
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun MenuScreen(
    viewModel: MenuScreenViewModel,
    typeList: List<IntakeItemCategory>,
    unitList: List<IntakeItemUnit>,
    onAddHandler: () -> Unit,
) {
    val quantity: Int by viewModel
        .quantity.observeAsState(0)

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Record Intake", style = MaterialTheme.typography.h1)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Intake type:")
                IntakeTypeMenu(viewModel, typeList)
            }
            Spacer(modifier = Modifier.height(Dp(16.0F)))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextField(
                    value = quantity.toString(),
                    onValueChange = { it ->
                        viewModel.updateQuantity(it.toInt())
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                IntakeUnitMenu(viewModel, unitList)
            }
            Spacer(modifier = Modifier.height(Dp(64.0F)))
            OutlinedButton(
                onClick = { onAddHandler() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add")
            }
        }
    }
}
