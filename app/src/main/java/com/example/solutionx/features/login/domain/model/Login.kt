package com.example.solutionx.features.login.domain.model


data class Login(
    val message :String ,
    val accessToken: String ,
    val userInfo: User,
)
