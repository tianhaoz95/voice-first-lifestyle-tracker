package com.tianhaoz95.lifestyletrackervoice_first.activities.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.record.AddActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.menu.MenuScreen
import com.tianhaoz95.lifestyletrackervoice_first.types.IntakeItemCategory
import com.tianhaoz95.lifestyletrackervoice_first.types.menu.MenuItem

class MenuActivity : AppCompatActivity() {
    private val itemList: List<MenuItem> = listOf(
        MenuItem(type = IntakeItemCategory.Soda),
        MenuItem(type = IntakeItemCategory.Coffee),
        MenuItem(type = IntakeItemCategory.Water),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuScreen(
                items = itemList,
                onItemClicked = { item -> onItemClickedHandler(item) })
        }
    }

    private fun onItemClickedHandler(item: MenuItem) {
        var intent = Intent(this, AddActivity::class.java)
        intent.putExtra("name", item.getType.toString())
        startActivity(intent)
    }
}