package com.tianhaoz95.lifestyletrackervoice_first.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import com.tianhaoz95.lifestyletrackervoice_first.activities.menu.MenuActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.report.ShowReportActivity
import com.tianhaoz95.lifestyletrackervoice_first.activities.settings.SettingsActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.main.MainScreen
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var userDataService: UserDataService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDataService.maybeNeedAuthentication(this)
        println("tianhaoz_debug: extras is ${intent.extras?.get("featureParam")}")
        setContent {
            MainScreen(
                addRecordHandler = { addRecordHandler() },
                reportsHandler = { reportsHandler() },
                settingsHandler = { settingsHandler() },
                signOutHandler = { signOutHandler() },
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        println("tianhaoz_debug: new intent extras is ${intent?.extras?.get("featureParam")}")
    }

    private fun addRecordHandler() {
        startActivity(Intent(this, MenuActivity::class.java))
    }

    private fun reportsHandler() {
        startActivity(Intent(this, ShowReportActivity::class.java))
    }

    private fun settingsHandler() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }

    private fun signOutHandler() {
        userDataService.signOut(this)
    }
}