package com.example.solutionx.features.model.signup

import com.example.solutionx.features.signup.domain.model.User


data class Signup(
    val message: String,
    val token: String,
    val user: User
)