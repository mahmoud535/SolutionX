package com.example.solutionx.features.authentication.login.domain.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val age: Int,

)
