package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.google.gson.Gson
import java.lang.reflect.Type
import javax.inject.Inject

internal class RestApiNetworkProvider  @Inject constructor(private val serviceApi: ServiceApi):IRestApiNetworkProvider {
    override suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody
    ): ResponseBody {
        val response  = serviceApi.post(
            pathUrl = pathUrl, headers = headers ?: hashMapOf(),
            queryParams = queryParams ?: hashMapOf(), requestBody = requestBody ?: Unit
        )

        return Gson().fromJson(response.string(), responseWrappedModel)
    }

    override suspend fun <ResponseBody, RequestBody> delete(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        TODO("Not yet implemented")
    }

    override suspend fun <ResponseBody, RequestBody> put(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        TODO("Not yet implemented")
    }

    override suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?
    ): ResponseBody {
        TODO("Not yet implemented")
    }


}