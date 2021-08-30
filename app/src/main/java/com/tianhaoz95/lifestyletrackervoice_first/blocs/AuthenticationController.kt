package com.tianhaoz95.lifestyletrackervoice_first.blocs

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class AuthenticationController @Inject constructor(
    @ActivityContext private val context: Context,
    private val activity: Activity,
    private val userDataService: UserDataService
) {
    fun signOut() {
        userDataService.signOut(context) {
            activity.finish()
        }
    }

    fun signIn(launcher: ActivityResultLauncher<Intent>) {
        launcher.launch(userDataService.getSignInIntent())
    }
}