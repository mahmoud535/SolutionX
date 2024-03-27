package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import com.example.solutionx.features.authentication.login.domain.model.User

sealed class LoginState {
    data class Success(val user: User?) : LoginState()
    object Loading : LoginState()
    data class Error(val message: String) : LoginState()
}
