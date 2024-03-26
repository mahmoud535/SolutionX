package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import com.example.solutionx.features.authentication.login.domain.model.User

sealed class LoginViewState {
    data class UserLoggedIn(val user: User?) : LoginViewState()
    object Loading : LoginViewState()
    data class Error(val message: String) : LoginViewState()
}
