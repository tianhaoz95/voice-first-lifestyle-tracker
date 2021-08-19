package com.tianhaoz95.lifestyletrackervoice_first.services

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleFitService @Inject constructor() {
    private val _options: FitnessOptions = FitnessOptions.builder()
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

    val options: FitnessOptions = _options
    val signInOptions: GoogleSignInOptions = GoogleSignInOptions
        .Builder()
        .addExtension(_options)
        .build()

    fun getAccount(context: Context): GoogleSignInAccount {
        return GoogleSignIn.getAccountForExtension(context, _options)
    }

    fun isLinked(context: Context): Boolean {
        val account = getAccount(context)
        return GoogleSignIn.hasPermissions(account, _options)
    }

    fun getHydrationDataset(context: Context, volume: Float): DataSet {
        val hydrationSource = DataSource.Builder()
            .setAppPackageName(context)
            .setDataType(DataType.TYPE_HYDRATION)
            .setStreamName("NutritionBook")
            .setType(DataSource.TYPE_RAW)
            .build()
        val timestamp = Calendar.getInstance().timeInMillis
        val hydration = DataPoint.builder(hydrationSource)
            .setTimestamp(timestamp, TimeUnit.MILLISECONDS)
            .setField(Field.FIELD_VOLUME, volume)
            .build()
        return DataSet.builder(hydrationSource)
            .add(hydration)
            .build()
    }
}