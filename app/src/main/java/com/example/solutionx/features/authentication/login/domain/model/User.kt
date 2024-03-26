package com.example.solutionx.features.authentication.login.domain.model

data class User(
    val id: Int,
    val email: String,
    val lastname: String,
    val middlename: String,
    val phone: Phone,
    val phone_verified: Boolean,
    val username: String,
    val number: String,
)
