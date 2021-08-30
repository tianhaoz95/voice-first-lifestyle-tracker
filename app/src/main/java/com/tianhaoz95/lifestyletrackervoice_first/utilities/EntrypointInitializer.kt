package com.tianhaoz95.lifestyletrackervoice_first.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.tianhaoz95.lifestyletrackervoice_first.activities.authentication.AuthenticationActivity
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class EntrypointInitializer @Inject constructor(
    @ActivityContext private val context: Context,
    private val userDataService: UserDataService
) {
    fun initialize(
        launcher: ActivityResultLauncher<Intent>,
        onAuthenticated: () -> Unit
    ) {
        if (!userDataService.isAuthenticated()) {
            launcher.launch(
                Intent(context, AuthenticationActivity::class.java))
        } else {
            onAuthenticated()
        }
    }
}