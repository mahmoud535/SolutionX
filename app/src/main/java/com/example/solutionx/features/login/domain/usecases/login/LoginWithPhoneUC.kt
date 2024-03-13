package com.example.solutionx.features.login.domain.usecases.login

import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.local.LocalRepo
import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
import javax.inject.Inject

class LoginWithPhoneUC @Inject constructor(
    private val remoteRepo: RemoteRepo,
    private val localRepo: LocalRepo
) {
    suspend operator fun invoke(phoneNumber: String, password: String): User {
        val user = remoteRepo.loginWithPhone(phoneNumber, password)
//        localRepo.saveUser(user)
        return user
    }
}