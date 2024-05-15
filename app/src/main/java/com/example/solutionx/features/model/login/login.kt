package com.solutionplus.altasherat.model.login

import com.solutionplus.altasherat.model.user.User

data class login(
    val message: String,
    val token: String,
    val user: User
)