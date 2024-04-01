package com.example.solutionx.features.login.data.repository.local

import com.example.solutionx.features.login.data.local.UserPreferences
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import kotlinx.coroutines.flow.Flow

internal class LoginLocalDS (private val userPreferences: UserPreferences): ILoginLocalDS {
    override suspend fun saveUser(login: LoginEntity) {
        userPreferences.saveUserData(login)
    }

    override suspend fun getAccessToken(): Flow<String?> {
        return userPreferences.accessToken
    }

    override suspend fun getUser():UserEntity=
         userPreferences.getUser()

}