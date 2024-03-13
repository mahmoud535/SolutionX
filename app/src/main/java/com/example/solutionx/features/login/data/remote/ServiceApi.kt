package com.example.solutionx.features.login.data.remote

import com.example.solutionx.features.login.data.model.UserEntity
import retrofit2.Response

interface ServiceApi {
    suspend fun loginWithEmail(email: String, password: String): Response<UserEntity>
    suspend fun loginWithPhone(phone: String, password: String): Response<UserEntity>
    suspend fun loginWithSocial(token: String): Response<UserEntity>
}