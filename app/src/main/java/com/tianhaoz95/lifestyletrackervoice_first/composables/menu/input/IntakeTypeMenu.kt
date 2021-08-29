package com.tianhaoz95.lifestyletrackervoice_first.composables.menu.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory

@Composable
fun IntakeTypeMenu(
    model: MenuScreenViewModel = viewModel()
) {
    val expand: Boolean by model
        .expandType.observeAsState(false)
    val index: Int by model
        .indexType.observeAsState(0)

    Box(modifier = Modifier.clickable {
        model.flipExpandType()
    }) {
        MenuTypeCard(model.typeList[index])
    }
    DropdownMenu(
        expanded = expand,
        onDismissRequest = {
            model.updateExpandType(false)
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        model.typeList.forEachIndexed { index, menuItem ->
            DropdownMenuItem(onClick = {
                model.updateTypeIndex(index)
                model.updateExpandType(false)
            }) {
                MenuTypeCard(menuItem)
            }
        }
    }
}