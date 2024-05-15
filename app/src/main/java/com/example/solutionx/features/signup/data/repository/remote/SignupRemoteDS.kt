package com.example.solutionx.features.signup.data.repository.remote

import com.example.solutionx.features.signup.data.model.dto.SignupDto
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.signup.domain.repository.remote.ISignupRemoteDS

internal class SignupRemoteDS(private val provider: IRestApiNetworkProvider) : ISignupRemoteDS {

    override suspend fun signupWithPhone(
        signupRequest: SignupRequest
    ): SignupDto? {
        return provider.post(
            responseWrappedModel = SignupDto::class.java, pathUrl = "signup",
            headers = hashMapOf("accept" to "application/json"), requestBody = signupRequest
        )
    }


}