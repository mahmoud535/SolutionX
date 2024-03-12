package com.example.solutionx.features.login.domain.repository.local

import com.example.solutionx.features.login.domain.model.User

interface LocalRepo {

    suspend fun saveUser(user: User)
    suspend fun getUser()
}