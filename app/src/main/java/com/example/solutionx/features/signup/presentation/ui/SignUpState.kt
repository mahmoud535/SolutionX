package com.example.solutionx.features.signup.presentation.ui

import com.example.solutionx.features.login.presentation.ui.fragment.login.LoginState
import com.example.solutionx.features.signup.domain.model.User

sealed class SignUpState {
    data class Success(val user: User?) : SignUpState()
    object Loading : SignUpState()
    data class Error(val message: String) : SignUpState()
}