package com.tianhaoz95.lifestyletrackervoice_first.activities.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.report.ShowReportScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.report.ShowReportViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.utilities.LocalSettingsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShowReportActivity : AppCompatActivity() {
    @Inject lateinit var localSettingsManager: LocalSettingsManager
    @Inject lateinit var userDataService: UserDataService
    private val viewModel: ShowReportViewModel = ShowReportViewModel()
    private val authenticateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { initializeData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!userDataService.isAuthenticated()) {
            authenticateLauncher.launch(
                Intent(this, AuthenticationActivity::class.java))
        } else {
            initializeData()
        }

        setContent {
            ShowReportScreen(viewModel)
        }

    }

    override fun onBackPressed() {
        finish()
    }

    private fun initializeData() {
        userDataService.initialize(
            getIsDeveloper = { localSettingsManager.getIsDeveloper() }
        )
        lifecycleScope.launch {
            fetchRecords()
        }
    }

    private fun fetchRecords() {
        userDataService.getCurrentDayHydration(
            onError = { code, msg ->
                viewModel.setNewStatus(
                    newStatus = code.toString(),
                    newDetails = msg
                )
            },
            onSuccess = {
                val cntStr: String = userDataService.recordCount.toString()
                viewModel.setNewStatus(
                    newStatus = "SUCCESS",
                    newDetails = "Found $cntStr records"
                )
            })
    }
}