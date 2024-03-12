package com.example.solutionx.features.login.domain.repository.remote

import com.example.solutionx.features.login.domain.model.User

interface RemoteRepo {
    suspend fun loginWithEmail(email: String, password: String): User
    suspend fun loginWithPhone(phone: String, password: String): User
    suspend fun loginWithSocial(token: String): User
}