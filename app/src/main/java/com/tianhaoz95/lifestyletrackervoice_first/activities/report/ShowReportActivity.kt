package com.tianhaoz95.lifestyletrackervoice_first.activities.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.report.ShowReportScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.report.ShowReportViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShowReportActivity : AppCompatActivity() {
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
            getIsDeveloper = { getDeveloperIdentity() }
        )
        lifecycleScope.launch {
            fetchRecords()
        }
    }

    private fun getDeveloperIdentity(): Boolean? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return if (sharedPref.contains("isDeveloper")) {
            sharedPref.getBoolean("isDeveloper", true)
        } else {
            null
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