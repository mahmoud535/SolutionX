package com.example.solutionx.features.authentication.login.domain.repository

import com.example.solutionx.features.authentication.login.domain.model.Phone
import com.example.solutionx.features.authentication.login.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.login.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
//    suspend fun loginWithEmail(email: String, password: String): Login
    suspend fun loginWithPhone(phone: Phone): Login
//    suspend fun loginWithSocial(token: String): Login
    suspend fun saveUser(login: Login)
     suspend fun getAccessToken(): Flow<String?>
     suspend fun getUser(): Flow<UserEntity?>
}