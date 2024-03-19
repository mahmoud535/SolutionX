package com.example.solutionx.features.login.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.features.login.domain.usecases.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.usecases.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.usecases.login.LoginWithSocialUC
import com.example.solutionx.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithSocialUC: LoginWithSocialUC
) : ViewModel() {

    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Loading)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun processIntent(intent: LoginIntent) {
        viewModelScope.launch {
            _viewState.emit(LoginViewState.Loading)
            try {
                when (intent) {
                    is LoginIntent.LoginWithEmail -> {
                       loginWithEmailUC(intent.email, intent.password).onEach{ userResource ->
                           when (userResource) {
                               is Resource.Loading -> _viewState.emit(LoginViewState.Loading)
                               is Resource.Success -> _viewState.emit(LoginViewState.UserLoggedIn(userResource.data))
                               is Resource.Failure -> _viewState.emit(LoginViewState.Error(userResource.message ?: "error in login"))
                           }
                       }.flowOn(Dispatchers.IO)
                    }
                    is LoginIntent.LoginWithPhone -> {
                         loginWithPhoneUC(intent.phone, intent.password).onEach{ userResource ->
                            when (userResource) {
                                is Resource.Loading -> _viewState.emit(LoginViewState.Loading)
                                is Resource.Success -> _viewState.emit(LoginViewState.UserLoggedIn(userResource.data))
                                is Resource.Failure -> _viewState.emit(LoginViewState.Error(userResource.message ?: "error in login"))
                            }
                        }.flowOn(Dispatchers.IO)
                    }
                    is LoginIntent.LoginWithSocial -> {
                        loginWithSocialUC(intent.token).onEach{ userResource ->
                            when (userResource) {
                                is Resource.Loading -> _viewState.emit(LoginViewState.Loading)
                                is Resource.Success -> _viewState.emit(LoginViewState.UserLoggedIn(userResource.data))
                                is Resource.Failure -> _viewState.emit(LoginViewState.Error(userResource.message ?: "error in login"))
                            }
                        }.flowOn(Dispatchers.IO)
                    }
                }
            } catch (e: Exception) {
                _viewState.emit(LoginViewState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}