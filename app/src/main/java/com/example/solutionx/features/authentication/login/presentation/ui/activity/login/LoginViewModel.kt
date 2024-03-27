package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.authentication.login.domain.interactor.login.LoginWithSocialUC
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

    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Loading)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun processLoginIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginWithEmail -> loginWithEmail(intent.email, intent.password)
            is LoginIntent.LoginWithPhone -> loginWithPhone(intent.phone)
            is LoginIntent.LoginWithSocial -> loginWithSocial(intent.token)

        }
    }

    private fun loginWithEmail(email: String, password: String) {
        viewModelScope.launch {
            loginWithEmailUC.invoke(viewModelScope, email, password) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update { LoginViewState.Loading }
                    is Resource.Success -> _viewState.update { LoginViewState.UserLoggedIn(resource.model.user) }
                    is Resource.Failure -> _viewState.update { LoginViewState.Error(resource.exception.message ?: "error in login") }
                }
            }
        }
    }

    private fun loginWithPhone(phoneNumber: Phone) {
        viewModelScope.launch {
            loginWithPhoneUC.invoke(viewModelScope, phoneNumber) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update { LoginViewState.Loading }
                    is Resource.Success -> _viewState.update { LoginViewState.UserLoggedIn(resource.model.user) }
                    is Resource.Failure -> _viewState.update { LoginViewState.Error(resource.exception.message ?: "error in login") }
                }
            }
        }
    }

    private fun loginWithSocial(token: String) {
        viewModelScope.launch {
            loginWithSocialUC.invoke(viewModelScope, token) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update { LoginViewState.Loading }
                    is Resource.Success -> _viewState.update { LoginViewState.UserLoggedIn(resource.model.user) }
                    is Resource.Failure -> _viewState.update { LoginViewState.Error(resource.exception.message ?: "error in login") }
                }
            }
        }
    }




}