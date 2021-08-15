package com.tianhaoz95.lifestyletrackervoice_first.activities.record

import android.os.Bundle
import androidx.activity.compose.setContent
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
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.record.AddRecordViewModel
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
    private val viewModel: AddRecordViewModel = AddRecordViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { 
            AddRecordScreen(viewModel)
        }

        lifecycleScope.launch {
            addRecord()
            delay(3000L)
            finish()
        }
    }

    private fun addRecord() {
        userDataService.addHydrationRecord(
            this,
            HydrationRecord.fromExtras(this.intent.extras),
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
}