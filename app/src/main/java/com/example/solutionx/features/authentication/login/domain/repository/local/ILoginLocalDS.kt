package com.example.solutionx.features.authentication.login.domain.repository.local

import com.example.solutionx.features.authentication.login.data.model.entity.LoginEntity
import com.example.solutionx.features.authentication.login.data.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

internal interface ILoginLocalDS {
    suspend fun saveUser(login: LoginEntity)
    suspend fun getAccessToken(): Flow<String?>
    suspend fun getUser(): Flow<UserEntity?>

}