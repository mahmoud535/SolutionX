package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import com.example.solutionx.features.authentication.login.domain.model.Phone

sealed class LoginIntent {
    data class LoginWithEmail(val email: String, val password: String) : LoginIntent()
    data class LoginWithPhone(val phone: Phone) : LoginIntent()
    data class LoginWithSocial(val token: String) : LoginIntent()
}