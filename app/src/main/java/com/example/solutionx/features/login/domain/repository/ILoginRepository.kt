package com.example.solutionx.features.login.domain.repository

import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.model.LoginRequest
import com.example.solutionx.features.login.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    suspend fun loginWithPhone(loginRequest: LoginRequest): Login
    suspend fun saveUser(login: Login)
     suspend fun getAccessToken(): Flow<String?>
     suspend fun getUser():UserEntity
}