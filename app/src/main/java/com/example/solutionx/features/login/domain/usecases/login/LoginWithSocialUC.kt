package com.example.solutionx.features.login.domain.usecases.login

import com.example.solutionx.features.login.data.repositoryimp.AuthRepositoryImpl
import com.example.solutionx.features.login.domain.model.User
import javax.inject.Inject

class LoginWithSocialUC @Inject constructor(private val remoteRepositoryImp: AuthRepositoryImpl){
    suspend operator fun invoke(token: String): User {
        return remoteRepositoryImp.loginWithSocial(token)
    }
}