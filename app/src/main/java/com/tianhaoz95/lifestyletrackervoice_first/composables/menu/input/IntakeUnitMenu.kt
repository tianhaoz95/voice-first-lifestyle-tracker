package com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun IntakeUnitMenu(
    viewModel: MenuScreenViewModel,
    unitList: List<IntakeItemUnit>,
) {
    val expand: Boolean by viewModel
        .expandUnit.observeAsState(false)
    val index: Int by viewModel
        .indexUnit.observeAsState(0)

    Box(modifier = Modifier.clickable {
        viewModel.flipExpandUnit()
    }) {
        MenuUnitCard(unitList[index])
    }
    DropdownMenu(
        expanded = expand,
        onDismissRequest = {
            viewModel.updateExpandUnit(false)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dp(10.0F)),
    ) {
        unitList.forEachIndexed { index, menuItem ->
            DropdownMenuItem(onClick = {
                viewModel.updateUnitIndex(index)
                viewModel.updateExpandUnit(false)
            }) {
                MenuUnitCard(menuItem)
            }
        }
    }
}