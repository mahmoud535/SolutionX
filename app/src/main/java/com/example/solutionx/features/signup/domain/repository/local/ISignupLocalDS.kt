package com.example.solutionx.features.signup.domain.repository.local

import com.example.solutionx.features.signup.data.model.entity.UserEntity

internal interface ISignupLocalDS {
    suspend fun saveUser(userEntity: UserEntity)
    suspend fun saveAccessToken(token: String)
    suspend fun getUser(): UserEntity

}