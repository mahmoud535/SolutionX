package com.example.solutionx.features.login.presentation.ui.fragment.login

sealed class LoginIntent {
    data class LoginWithEmail(val email: String, val password: String) : LoginIntent()
    data class LoginWithPhone(val phoneNumber: String, val countryCode: String, val password: String) : LoginIntent()
    data class LoginWithSocial(val token: String) : LoginIntent()
}