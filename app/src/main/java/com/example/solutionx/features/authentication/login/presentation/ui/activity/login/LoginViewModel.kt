package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithSocialUC
import com.example.solutionx.features.authentication.login.domain.model.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithSocialUC: LoginWithSocialUC,
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginState>(LoginState.Loading)
    val viewState: StateFlow<LoginState> = _viewState

    fun processLoginIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginWithEmail -> TODO()
            is LoginIntent.LoginWithPhone -> loginWithPhone(intent.phoneNumber,intent.countryCode,intent.password)
            is LoginIntent.LoginWithSocial -> TODO()

        }
    }

//    private fun loginWithEmail(email: String, password: String) {
//        viewModelScope.launch {
//            loginWithEmailUC.invoke(viewModelScope, email, password) { resource ->
//                when (resource) {
//                    is Resource.Progress -> _viewState.update { LoginViewState.Loading }
//                    is Resource.Success -> _viewState.update { LoginViewState.UserLoggedIn(resource.model.user) }
//                    is Resource.Failure -> _viewState.update { LoginViewState.Error(resource.exception.message ?: "error in login") }
//                }
//            }
//        }
//    }

//    private fun loginWithPhone() {
//        viewModelScope.launch {
//            val loginRequest = LoginRequest(
//                _viewState.value.countryCode,
//                _viewState.value.phoneNumber,
//                _viewState.value.password
//            )
//            loginWithPhoneUC.invoke(viewModelScope, loginRequest) { resource ->
//                when (resource) {
//                    is Resource.Progress -> _viewState.update { it.copy(isLoading = true)}
//                    is Resource.Success -> {
//                        _viewState.update { it.copy(userInfo = resource.model!!.toUserState())  } }
//                    is Resource.Failure -> _viewState.update { it.copy(isError = true) }
//                }
//            }
//        }
//    }

    private fun loginWithPhone(phoneNumber: String, countryCode: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(countryCode, phoneNumber, password)
            loginWithPhoneUC.invoke(viewModelScope, loginRequest) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update {LoginState.Loading }
                    is Resource.Success -> _viewState.update { LoginState.Success(resource.model) }
                    is Resource.Failure -> _viewState.update { LoginState.Error(resource.exception.message ?: "error in login") }
                }
            }
        }
    }

//    private fun loginWithSocial(token: String) {
//        viewModelScope.launch {
//            loginWithSocialUC.invoke(viewModelScope, token) { resource ->
//                when (resource) {
//                    is Resource.Progress -> _viewState.update { LoginViewState.Loading }
//                    is Resource.Success -> _viewState.update { LoginViewState.UserLoggedIn(resource.model.user) }
//                    is Resource.Failure -> _viewState.update { LoginViewState.Error(resource.exception.message ?: "error in login") }
//                }
//            }
//        }
//    }




}