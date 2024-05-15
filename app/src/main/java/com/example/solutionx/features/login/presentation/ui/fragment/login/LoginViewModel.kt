package com.example.solutionx.features.login.presentation.ui.fragment.login

import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.presentation.viewModel.BaseViewModel
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import com.example.solutionx.features.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithSocialUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithSocialUC: LoginWithSocialUC,
) : BaseViewModel<LoginState, LoginIntent>() {
    override val initialState: LoginState = LoginState.Loading
    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginWithEmail -> TODO()
            is LoginIntent.LoginWithPhone -> loginWithPhone(intent.phoneNumber,intent.countryCode,intent.password)
            is LoginIntent.LoginWithSocial -> TODO()

        }
    }

    private fun loginWithPhone(phoneNumber: String, countryCode: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                phone = Phone(countryCode, phoneNumber),
                password = password
            )
            loginWithPhoneUC.invoke(viewModelScope, loginRequest) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update { LoginState.Loading }
                    is Resource.Success ->{
                        _viewState.update { LoginState.Success(resource.model) }
                    }
                    is Resource.Failure -> _viewState.update { LoginState.Error(resource.exception.message ?: "error in login") }
                }
            }
        }
    }

}