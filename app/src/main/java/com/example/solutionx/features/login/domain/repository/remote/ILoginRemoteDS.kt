package com.example.solutionx.features.login.domain.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.request.LoginRequest

internal interface ILoginRemoteDS {
//    suspend fun loginWithEmail(email: String, password: String): LoginDto
suspend fun loginWithPhone(loginRequest: LoginRequest):LoginDto?

//    suspend fun loginWithSocial(token: String): LoginDto
}