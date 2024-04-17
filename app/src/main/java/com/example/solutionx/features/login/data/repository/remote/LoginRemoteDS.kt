package com.example.solutionx.features.login.data.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS

internal class LoginRemoteDS(private val provider: IRestApiNetworkProvider) : ILoginRemoteDS {

    override suspend fun loginWithPhone(
        loginRequest: LoginRequest
    ): LoginDto? {
        return provider.post(
            responseWrappedModel = LoginDto::class.java, pathUrl = "login",
            headers = hashMapOf("Accept-Language" to "ar"), requestBody = loginRequest
        )
    }

}