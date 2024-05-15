package com.example.solutionx.features.signup.presentation.ui

sealed class SignUpIntent {
    data class SignUp(val firstName:String,val lastName: String,val email:String,val phoneNumber: String, val countryCode: String, val password: String) : SignUpIntent()
}