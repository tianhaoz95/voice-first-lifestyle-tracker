package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.fitness.Fitness
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.GoogleFitService
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AddRecordScreen(viewModel)
        }

        lifecycleScope.launch {
            val record: HydrationRecord = getRecord()
            addRecord(record)
            maybeAddRecordToGoogleFit(record)
            delay(3000L)
            finish()
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