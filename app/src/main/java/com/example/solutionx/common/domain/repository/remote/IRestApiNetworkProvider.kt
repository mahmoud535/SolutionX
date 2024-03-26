package com.example.solutionx.common.domain.repository.remote

import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.domain.model.Phone
import retrofit2.Response

internal interface IRestApiNetworkProvider {

    suspend fun <T> get(url: String): T
    suspend fun <T> post( body: Any): T
    suspend fun <T> put(url: String, body: Any): T
    suspend fun <T> delete(url: String): T

}