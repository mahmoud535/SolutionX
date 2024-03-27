package com.example.solutionx.features.authentication.login.data.repository.remote

import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.data.remote.ServiceApi
import com.example.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS
import javax.inject.Inject

internal class LoginRemoteDS @Inject constructor(private val api: ServiceApi) : ILoginRemoteDS {

//    override suspend fun loginWithPhone(phone: Phone): LoginDto {
//        val body = hashMapOf("phone" to phone)
//        return api.post(body)
//    }

    override suspend fun loginWithPhone(
        countryCode: String,
        number: String,
        password: String
    ): LoginDto =
        api.loginWithPhone(countryCode, number, password)



}