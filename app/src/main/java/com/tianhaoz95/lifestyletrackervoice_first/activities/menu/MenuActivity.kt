package com.tianhaoz95.lifestyletrackervoice_first.activities.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.record.AddActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.MenuScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit
import com.tianhaoz95.lifestyletrackervoice_first.types.menu.MenuItem

class MenuActivity : AppCompatActivity() {
    private val viewModel: MenuScreenViewModel = MenuScreenViewModel()
    private val typeList: List<IntakeItemCategory> = listOf(
        IntakeItemCategory.Soda,
        IntakeItemCategory.Coffee,
        IntakeItemCategory.Water,
    )
    private val unitList: List<IntakeItemUnit> = listOf(
        IntakeItemUnit.Liter,
        IntakeItemUnit.Milliliter,
        IntakeItemUnit.Can,
        IntakeItemUnit.Bottle,
        IntakeItemUnit.Cup,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuScreen(
                viewModel= viewModel,
                typeList = typeList,
                unitList = unitList,
                onAddHandler = { onAddHandler() }
            )
        }
    }

    private fun onAddHandler() {
        val intent = Intent(this, AddActivity::class.java)
        val selectedItem = typeList[viewModel.getCurrentTypeIndex()]
        intent.putExtra("name", selectedItem.toString())
        startActivity(intent)
    }
}