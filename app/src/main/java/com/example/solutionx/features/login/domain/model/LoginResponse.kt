package com.example.solutionx.features.login.domain.model

data class LoginResponse(
    val accessToken: String,
    val userInfo: User
)
