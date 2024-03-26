package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.authentication.login.data.remote.ServiceApi
import javax.inject.Inject

internal class RestApiNetworkProvider  @Inject constructor(private val serviceApi: ServiceApi):IRestApiNetworkProvider {
    override suspend fun <T> get(url: String): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> post( body: Any): T {
        val response =  serviceApi.loginWithPhone(body)
        return response.body() as T
    }

    override suspend fun <T> put(url: String, body: Any): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> delete(url: String): T {
        TODO("Not yet implemented")
    }

}