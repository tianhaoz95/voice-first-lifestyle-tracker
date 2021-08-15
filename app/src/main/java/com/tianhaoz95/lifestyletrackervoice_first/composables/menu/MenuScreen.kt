package com.tianhaoz95.lifestyletrackervoice_first.composables.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.menu.MenuItem

@Composable
fun MenuItemCard(item: MenuItem, onItemClicked: (item: MenuItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dp(6.0F)),
        elevation = Dp(2.0F),
    ) {
        Column() {
            Text(text = item.getType.toString())
            Button(
                onClick = { onItemClicked(item) }, modifier = Modifier.padding(
                    Dp(2.0F)
                ).fillMaxWidth()
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun MenuScreen(items: List<MenuItem>, onItemClicked: (item: MenuItem) -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        items.forEach {
            MenuItemCard(it, onItemClicked)
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    var itemList: MutableList<MenuItem> = mutableListOf()
    for (i in 1..100) {
        itemList.add(MenuItem(type = IntakeItemCategory.Soda))
    }
    MenuScreen(items = itemList.toList(), onItemClicked = {})
}