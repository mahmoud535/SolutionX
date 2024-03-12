package com.example.solutionx.features.login.data.remote

import com.example.solutionx.features.login.domain.model.LoginResponse
import com.example.solutionx.features.login.domain.model.User
import retrofit2.Response

interface ServiceApi {
    suspend fun loginWithEmail(email: String, password: String): Response<LoginResponse>
    suspend fun loginWithPhone(phone: String, password: String): Response<LoginResponse>
    suspend fun loginWithSocial(token: String): Response<LoginResponse>
}