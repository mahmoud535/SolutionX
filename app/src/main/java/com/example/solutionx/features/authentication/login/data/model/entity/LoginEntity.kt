package com.example.solutionx.features.authentication.login.data.model.entity

internal data class LoginEntity (
    val message: String,
    val token: String,
    val user: UserEntity
)