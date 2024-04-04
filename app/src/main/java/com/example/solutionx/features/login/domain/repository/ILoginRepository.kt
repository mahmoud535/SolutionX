package com.example.solutionx.features.login.domain.repository

import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    suspend fun loginWithPhone(loginRequest: LoginRequest): Login
    suspend fun saveUser(user: User)
    suspend fun saveAccessToken(token: String)
    suspend fun getUser(): UserEntity?
}