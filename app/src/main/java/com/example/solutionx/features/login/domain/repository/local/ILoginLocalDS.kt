package com.example.solutionx.features.login.domain.repository.local

import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

internal interface ILoginLocalDS {
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun saveAccessToken(token: String)
    suspend fun getUser():UserEntity

}