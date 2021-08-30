package com.tianhaoz95.lifestyletrackervoice_first.activities.authentication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.tianhaoz95.lifestyletrackervoice_first.blocs.AuthenticationController
import com.tianhaoz95.lifestyletrackervoice_first.composables.login.LoginScreen
import com.tianhaoz95.lifestyletrackervoice_first.models.LoginScreenModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    companion object {
        private const val TAG: String = "AuthenticationActivity"
    }

    @Inject
    lateinit var userDataService: UserDataService

    @Inject
    lateinit var authenticationController: AuthenticationController
    private val model: LoginScreenModel by viewModels()
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res -> this.onSignInResult(res) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen {
                authenticationController.signIn(signInLauncher)
            }
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
            userDataService.addRemoteCrashLog(
                """
                    User login failed with result code ${result.resultCode} and
                    idpResponse ${result.idpResponse.toString()}.
                """.trimIndent(), TAG
            )
        }
    }
}