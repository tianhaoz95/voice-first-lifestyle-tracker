package com.tianhaoz95.lifestyletrackervoice_first.activities.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.tianhaoz95.lifestyletrackervoice_first.composables.login.LoginScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.LoginScreenModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    @Inject lateinit var userDataService: UserDataService
    private val model: LoginScreenModel by viewModels()
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res -> this.onSignInResult(res) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.setLauncher(signInLauncher)
        setContent {
            LoginScreen()
        }
    }

    override fun onBackPressed() {}

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            finish()
        } else {
            model.setNewStatus(
                result.resultCode.toString(),
                result.idpResponse.toString()
            )
        }
    }
}