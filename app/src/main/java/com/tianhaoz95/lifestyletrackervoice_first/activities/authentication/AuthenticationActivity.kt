package com.tianhaoz95.lifestyletrackervoice_first.activities.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.tianhaoz95.lifestyletrackervoice_first.composables.login.LoginScreen
import com.tianhaoz95.lifestyletrackervoice_first.composables.login.LoginViewModel
import com.tianhaoz95.lifestyletrackervoice_first.services.UserDataService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    @Inject lateinit var userDataService: UserDataService
    private val viewModel: LoginViewModel = LoginViewModel()
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res -> this.onSignInResult(res) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                viewModel = viewModel,
                onSignIn = {
                    viewModel.setNewStatus(
                        "",
                        ""
                    )
                    signInLauncher.launch(userDataService.getSignInIntent())
                }
            )
        }
    }

    override fun onBackPressed() {}

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            finish()
        } else {
            viewModel.setNewStatus(
                result.resultCode.toString(),
                result.idpResponse.toString()
            )
        }
    }
}