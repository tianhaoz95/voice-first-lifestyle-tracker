package com.tianhaoz95.lifestyletrackervoice_first.activities.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field.FIELD_VOLUME
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.settings.SettingsViewModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class SettingsActivity : AppCompatActivity() {
    private val viewModel: SettingsViewModel = SettingsViewModel()
    private val linkGoogleFitLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { res ->
            onResultFromLinkGoogleFit(res)
        }

    private fun launchLinkGoogleFitIntent() {
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(
                DataType.TYPE_NUTRITION, FitnessOptions.ACCESS_WRITE
            )
            .addDataType(
                DataType.TYPE_NUTRITION, FitnessOptions.ACCESS_READ
            )
            .addDataType(
                DataType.TYPE_HYDRATION, FitnessOptions.ACCESS_WRITE
            )
            .addDataType(
                DataType.TYPE_HYDRATION, FitnessOptions.ACCESS_READ
            )
            .build()
        val account = GoogleSignIn.getAccountForExtension(
            this, fitnessOptions
        )
        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                this, // your activity
                1, // e.g. 1
                account,
                fitnessOptions
            )
        } else {
            println("tianhaoz_debug: already has fit linked")
            val signedInAcct = GoogleSignIn.getAccountForExtension(
                this, fitnessOptions)
            val refreshedAccount = GoogleSignIn.getAccountForExtension(
                this, fitnessOptions)
            val hydrationSource = DataSource.Builder()
                .setDataType(DataType.TYPE_HYDRATION)
                .setStreamName("Nutrition Book")
                .build()
            val timestamp = Calendar.getInstance().timeInMillis
            val hydration = DataPoint.builder(hydrationSource)
                .setTimestamp(timestamp, TimeUnit.MILLISECONDS)
                .setField(FIELD_VOLUME, 0.3f)
                .build()
            val dataSet = DataSet.builder(hydrationSource)
                .add(hydration)
                .build()
            Fitness.getHistoryClient(this, refreshedAccount)
                .insertData(dataSet)
                .addOnSuccessListener({ response ->
                    println("tianhaoz_debug: add water success")
                })
                .addOnFailureListener({ e ->
                    println("tianhaoz_debug: add water failed")
                })
        }
    }

    private fun onResultFromLinkGoogleFit(res: ActivityResult) {
        println("tianhaoz_debug: link acct result ${res.resultCode}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchCrashReporting()
        setContent {
            SettingsScreen(
                viewModel = viewModel,
                onShouldReportCrashChange = {
                    updateCrashReporting(it)
                },
                linkGoogleFitHandler = { launchLinkGoogleFitIntent() }
            )
        }
    }

    private fun fetchCrashReporting() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val shouldReportCrash = sharedPref.getBoolean("shouldReportCrash", true)
        viewModel.updateShouldReportCrash(shouldReportCrash)
    }

    private fun updateCrashReporting(update: Boolean) {
        viewModel.updateShouldReportCrash(update)
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("shouldReportCrash", update)
            apply()
        }
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(update)
    }
}