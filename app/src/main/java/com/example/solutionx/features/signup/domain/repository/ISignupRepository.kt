package com.example.solutionx.features.signup.domain.repository

import com.example.solutionx.features.signup.data.model.entity.UserEntity
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.model.signup.Signup
import com.example.solutionx.features.signup.domain.model.User

interface ISignupRepository {
    suspend fun signupWithPhone(signupRequest: SignupRequest): Signup
    suspend fun saveUser(user: User)
    suspend fun saveAccessToken(token: String)
    suspend fun getUser(): UserEntity?
}