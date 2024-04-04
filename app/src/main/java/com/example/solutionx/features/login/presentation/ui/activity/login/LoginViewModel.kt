package com.example.solutionx.features.login.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithSocialUC
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.http2.Http2Reader.Companion.logger
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithSocialUC: LoginWithSocialUC,
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginState>(LoginState.Loading)
    val viewState = _viewState.asStateFlow()

    fun processLoginIntent(intent: LoginIntent) {
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