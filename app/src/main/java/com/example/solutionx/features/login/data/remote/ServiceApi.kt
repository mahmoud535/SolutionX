package com.example.solutionx.features.login.data.remote

import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.data.model.UserEntity
import retrofit2.Response

interface ServiceApi {
    suspend fun loginWithEmail(email: String, password: String): Response<UserDto>
    suspend fun loginWithPhone(phone: String, password: String): Response<UserDto>
    suspend fun loginWithSocial(token: String): Response<UserDto>
}