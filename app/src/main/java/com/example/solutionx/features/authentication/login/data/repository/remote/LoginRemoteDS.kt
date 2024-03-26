package com.example.solutionx.features.authentication.login.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.domain.model.Phone
import com.example.solutionx.features.authentication.login.data.remote.ServiceApi
import com.example.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS
import javax.inject.Inject

internal class LoginRemoteDS @Inject constructor(private val api: IRestApiNetworkProvider) : ILoginRemoteDS {
//    override suspend fun loginWithEmail(email: String, password: String): LoginDto =
//        api.loginWithEmail(email, password)

    override suspend fun loginWithPhone(phone: Phone): LoginDto =
         api.post( phone)

//    override suspend fun loginWithSocial(token: String): LoginDto =
//        api.loginWithSocial(token)


}