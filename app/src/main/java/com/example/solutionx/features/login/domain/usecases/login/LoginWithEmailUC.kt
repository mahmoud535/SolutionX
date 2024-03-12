package com.example.solutionx.features.login.domain.usecases.login

import com.example.solutionx.features.login.data.repositoryimp.AuthRepositoryImpl
import com.example.solutionx.features.login.domain.model.User
import javax.inject.Inject

class LoginWithEmailUC @Inject constructor(private val remoteRepositoryImp: AuthRepositoryImpl){
    suspend operator fun invoke(email: String, password: String): User {
        return remoteRepositoryImp.loginWithEmail(email, password)
    }
}