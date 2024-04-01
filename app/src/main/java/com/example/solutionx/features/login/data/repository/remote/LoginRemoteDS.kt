package com.example.solutionx.features.login.data.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.features.login.domain.model.LoginRequest
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import javax.inject.Inject

internal class LoginRemoteDS (private val api: ServiceApi) : ILoginRemoteDS {

    override suspend fun loginWithPhone(
        loginRequest: LoginRequest
    ): LoginDto? {
       val request =  api.loginWithPhone(loginRequest)
      return request.body()
    }

}