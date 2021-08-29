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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

@Composable
fun IntakeUnitMenu(
    unitList: List<IntakeItemUnit>,
    model: MenuScreenViewModel = viewModel()
) {
    val expand: Boolean by model
        .expandUnit.observeAsState(false)
    val index: Int by model
        .indexUnit.observeAsState(0)

    Box(modifier = Modifier.clickable {
        model.flipExpandUnit()
    }) {
        MenuUnitCard(unitList[index])
    }
    DropdownMenu(
        expanded = expand,
        onDismissRequest = {
            model.updateExpandUnit(false)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dp(10.0F)),
    ) {
        unitList.forEachIndexed { index, menuItem ->
            DropdownMenuItem(onClick = {
                model.updateUnitIndex(index)
                model.updateExpandUnit(false)
            }) {
                MenuUnitCard(menuItem)
            }
        }
    }
}