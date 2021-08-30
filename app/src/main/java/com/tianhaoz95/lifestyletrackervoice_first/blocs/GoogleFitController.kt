package com.tianhaoz95.lifestyletrackervoice_first.blocs

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.tianhaoz95.lifestyletrackervoice_first.services.integrations.GoogleFitService
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
}