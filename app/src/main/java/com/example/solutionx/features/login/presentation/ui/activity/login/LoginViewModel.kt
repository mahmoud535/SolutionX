package com.example.solutionx.features.login.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.usecases.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.usecases.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.usecases.login.LoginWithSocialUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val loginWithEmailUC: LoginWithEmailUC,
    private val loginWithSocialUC: LoginWithSocialUC
) : ViewModel() {


    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    fun loginWithEmail(email: String, password: String) {
      viewModelScope.launch {
          _user.emit(loginWithEmailUC(email, password))
      }
    }

    fun loginWithPhone(phone: String, password: String) {
        viewModelScope.launch {
            _user.emit(loginWithPhoneUC(phone,password))
        }
    }

    fun loginWithSocial(token: String) {
        viewModelScope.launch {
            _user.emit(loginWithSocialUC(token))
        }
    }


    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Loading)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun processIntent(intent: LoginIntent) {
        viewModelScope.launch {
            _viewState.emit(LoginViewState.Loading)
            try {
                when (intent) {
                    is LoginIntent.LoginWithEmail -> {
                        val user = loginWithEmailUC(intent.email, intent.password)
                        _viewState.emit(LoginViewState.UserLoggedIn(user))
                    }
                    is LoginIntent.LoginWithPhone -> {
                        val user = loginWithPhoneUC(intent.phone, intent.password)
                        _viewState.emit(LoginViewState.UserLoggedIn(user))
                    }
                    is LoginIntent.LoginWithSocial -> {
                        val user = loginWithSocialUC(intent.token)
                        _viewState.emit(LoginViewState.UserLoggedIn(user))
                    }
                }
            } catch (e: Exception) {
                _viewState.emit(LoginViewState.Error(e.message ?: "Unknown error"))
            }
        }
    }
}