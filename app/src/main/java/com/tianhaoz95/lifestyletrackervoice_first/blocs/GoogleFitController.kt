package com.tianhaoz95.lifestyletrackervoice_first.blocs

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.tianhaoz95.lifestyletrackervoice_first.activities.record.AddActivity
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
import com.tianhaoz95.lifestyletrackervoice_first.types.HydrationRecord
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class GoogleFitController @Inject constructor(
    @ActivityContext private val context: Context,
    private val activity: Activity,
    private val googleFitService: GoogleFitService
) {
    companion object {
        private const val TAG: String = "SettingsActivity"
        private const val linkGoogleFitRequestCode: Int = 1
    }

    fun link() {
        val account = googleFitService.getAccount(context)
        if (!googleFitService.isLinked(context)) {
            GoogleSignIn.requestPermissions(
                activity,
                linkGoogleFitRequestCode,
                account,
                googleFitService.options
            )
        }
    }

    fun unlink(onUnlinkDone: (hasError: Boolean, msg: String) -> Unit) {
        Fitness.getConfigClient(
            activity, googleFitService.getAccount(context)
        )
            .disableFit()
            .addOnSuccessListener {
                GoogleSignIn
                    .getClient(activity, googleFitService.signInOptions)
                    .revokeAccess()
                onUnlinkDone(false, "")
            }
            .addOnFailureListener { e ->
                onUnlinkDone(true, e.message.orEmpty())
            }
    }

    fun maybeAddHydration(record: HydrationRecord) {
        if (googleFitService.isLinked(context)) {
            addHydration(record)
        }
    }

    private fun addHydration(record: HydrationRecord) {
        Fitness.getHistoryClient(
            activity,
            googleFitService.getAccount(context)
        )
            .insertData(
                googleFitService.getHydrationDataset(
                    context, 0.5F
                )
            )
            .addOnSuccessListener { response ->
                Log.i(TAG, "Add to Fit response: $response")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Add to Fit error: ${e.message}")
            }
    }
}