package com.example.solutionx.features.login.domain.repository.remote

import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.model.UserInfo

interface RemoteRepo {
    suspend fun loginWithEmail(email: String, password: String): UserInfo
    suspend fun loginWithPhone(phone: String, password: String): UserInfo
    suspend fun loginWithSocial(token: String): UserInfo
}