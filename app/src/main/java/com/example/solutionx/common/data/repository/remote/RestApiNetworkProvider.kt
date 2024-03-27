package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.data.remote.ServiceApi
import retrofit2.Response
import javax.inject.Inject

internal class RestApiNetworkProvider  @Inject constructor(private val serviceApi: ServiceApi):IRestApiNetworkProvider {
    override suspend fun <T> get(
        url: String,
        query: Map<String, Any>,
        headers: Map<String, String>
    ): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> post(body: Map<String, Any>): T {
        val response = serviceApi.loginWithPhone(body)
        if (response.body() == null) {
            throw IllegalStateException("Response body is null")
        }
        return response.body() as T
    }

    override suspend fun <T> put(
        url: String,
        body: Map<String, Any>,
        query: Map<String, Any>,
        headers: Map<String, String>
    ): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> delete(
        url: String,
        query: Map<String, Any>,
        headers: Map<String, String>
    ): T {
        TODO("Not yet implemented")
    }

}