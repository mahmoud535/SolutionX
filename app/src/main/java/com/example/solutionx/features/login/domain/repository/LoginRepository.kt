package com.example.solutionx.features.login.domain.repository

import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.data.model.UserEntity
import retrofit2.Response

interface LoginRepository {
    suspend fun loginWithEmail(email: String, password: String): Response<UserDto>
    suspend fun loginWithPhone(phone: String, password: String): Response<UserDto>
    suspend fun loginWithSocial(token: String): Response<UserDto>
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser()
}