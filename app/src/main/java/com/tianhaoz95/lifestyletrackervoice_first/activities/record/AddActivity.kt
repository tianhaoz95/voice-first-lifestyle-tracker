package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.fitness.Fitness
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddActivity : AppCompatActivity() {
    @Inject
    lateinit var userDataService: UserDataService

    @Inject
    lateinit var googleFitService: GoogleFitService
    private val tag: String = "AddActivity"
    private val viewModel: AddRecordViewModel = AddRecordViewModel()
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
            AddRecordScreen(viewModel)
        }
    }

    private fun initializeData() {
        userDataService.initialize(
            context = this,
            getIsDeveloper = { getDeveloperIdentity() }
        )
        lifecycleScope.launch {
            val record: HydrationRecord = getRecord()
            addRecord(record)
            maybeAddRecordToGoogleFit(record)
            delay(3000L)
            finish()
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

    private fun getRecord(): HydrationRecord {
        return HydrationRecord.fromExtras(this.intent.extras)
    }

    private fun addRecord(record: HydrationRecord) {
        userDataService.addHydrationRecord(
            this,
            record,
            onError = { code, msg ->
                viewModel.setNewStatus(
                    newStatus = code.toString(),
                    newDetails = msg
                )
            },
            onSuccess = {
                viewModel.setNewStatus(
                    newStatus = "SUCCESS",
                    newDetails = "The record has been added."
                )
            }
        )
    }

    private fun maybeAddRecordToGoogleFit(record: HydrationRecord) {
        if (googleFitService.isLinked(this)) {
            Fitness.getHistoryClient(
                this,
                googleFitService.getAccount(this)
            )
                .insertData(
                    googleFitService.getHydrationDataset(
                        this, 0.5F
                    )
                )
                .addOnSuccessListener { response ->
                    Log.i(
                        tag,
                        "Add to Fit response: $response"
                    )
                }
                .addOnFailureListener { e ->
                    Log.e(tag, "Add to Fit error: ${e.message}")
                }
        }
    }
}