package com.tianhaoz95.lifestyletrackervoice_first.activities.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.record.AddActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.MenuScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.MenuScreenViewModel
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemUnit

class MenuActivity : AppCompatActivity() {
    private val model: MenuScreenViewModel by viewModels()
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
                viewModel= model,
                typeList = typeList,
                unitList = unitList,
                onAddHandler = { onAddHandler() }
            )
        }
    }

    private fun onAddHandler() {
        val intent = Intent(this, AddActivity::class.java)
        val selectedItem = typeList[model.getCurrentTypeIndex()]
        intent.putExtra("name", selectedItem.toString())
        startActivity(intent)
    }
}