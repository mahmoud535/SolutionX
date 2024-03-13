package com.example.solutionx.features.login.presentation.ui.activity.login

sealed class LoginIntent {
    data class LoginWithEmail(val email: String, val password: String) : LoginIntent()
    data class LoginWithPhone(val phone: String, val password: String) : LoginIntent()
    data class LoginWithSocial(val token: String) : LoginIntent()
}