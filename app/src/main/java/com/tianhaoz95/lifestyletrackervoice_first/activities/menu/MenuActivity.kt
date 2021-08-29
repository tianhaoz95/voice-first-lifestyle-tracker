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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {
    private val model: MenuScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuScreen(onAddHandler = { onAddHandler() })
        }
    }

    private fun onAddHandler() {
        val intent = Intent(this, AddActivity::class.java)
        val selectedItem = model.typeList[model.getCurrentTypeIndex()]
        intent.putExtra("name", selectedItem.toString())
        startActivity(intent)
    }
}