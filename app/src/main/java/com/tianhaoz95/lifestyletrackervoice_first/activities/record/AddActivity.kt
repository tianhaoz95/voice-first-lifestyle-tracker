package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.blocs.GoogleFitController
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.AddRecordViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import com.tianhaoz95.lifestyletrackervoice_first.utilities.EntrypointInitializer
import com.tianhaoz95.lifestyletrackervoice_first.utilities.LocalSettingsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "AddActivity"
    }

    @Inject
    lateinit var localSettingsManager: LocalSettingsManager

    @Inject
    lateinit var userDataService: UserDataService

    @Inject
    lateinit var googleFitService: GoogleFitService

    @Inject
    lateinit var googleFitController: GoogleFitController

    @Inject
    lateinit var entrypointInitializer: EntrypointInitializer
    private val model: AddRecordViewModel by viewModels()
    private val authenticateLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { initializeData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entrypointInitializer.initialize(
            authenticateLauncher
        ) { initializeData() }

        setContent {
            AddRecordScreen()
        }
    }

    private fun initializeData() {
        userDataService.initialize(
            getIsDeveloper = { localSettingsManager.getIsDeveloper() }
        )
        lifecycleScope.launch {
            val record: HydrationRecord = getRecord()
            addRecord(record)
            googleFitController.maybeAddHydration(record)
            delay(3000L)
            finish()
        }
    }

    private fun getRecord(): HydrationRecord {
        return HydrationRecord.fromExtras(this.intent.extras)
    }

    private fun addRecord(record: HydrationRecord) {
        userDataService.addHydrationRecord(
            record,
            onError = { code, msg ->
                model.setNewStatus(code.toString(), msg)
            },
            onSuccess = {
                model.setNewStatus(
                    "SUCCESS", "The record has been added."
                )
            }
        )
    }
}