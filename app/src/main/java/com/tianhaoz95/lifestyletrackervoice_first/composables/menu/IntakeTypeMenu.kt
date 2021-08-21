package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory

@Composable
fun IntakeTypeMenu(
    viewModel: MenuScreenViewModel,
    typeList: List<IntakeItemCategory>,
) {
    val expand: Boolean by viewModel
        .expandType.observeAsState(false)
    val index: Int by viewModel
        .indexType.observeAsState(0)

    Box(modifier = Modifier.clickable {
        viewModel.flipExpandType()
    }) {
        MenuTypeCard(typeList[index])
    }
    DropdownMenu(
        expanded = expand,
        onDismissRequest = {
            viewModel.updateExpandType(false)
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        typeList.forEachIndexed { index, menuItem ->
            DropdownMenuItem(onClick = {
                viewModel.updateTypeIndex(index)
                viewModel.updateExpandType(false)
            }) {
                MenuTypeCard(menuItem)
            }
        }
    }
}