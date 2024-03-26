package com.example.solutionx.features.authentication.login.domain.repository.remote

import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.domain.model.Phone

internal interface ILoginRemoteDS {
//    suspend fun loginWithEmail(email: String, password: String): LoginDto
    suspend fun loginWithPhone(phone: Phone): LoginDto
//    suspend fun loginWithSocial(token: String): LoginDto
}