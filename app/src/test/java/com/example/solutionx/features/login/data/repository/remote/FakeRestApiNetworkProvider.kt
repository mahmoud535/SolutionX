package com.example.solutionx.features.login.data.repository.remote

import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import java.lang.reflect.Type

class FakeRestApiNetworkProvider : IRestApiNetworkProvider {

    var postResponse: Any? = null
    override suspend fun <ResponseBody, RequestBody> post(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody
    ): ResponseBody {
        @Suppress("UNCHECKED_CAST")
        return postResponse as ResponseBody
    }

    override suspend fun <ResponseBody, RequestBody> delete(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        throw UnsupportedOperationException(" ")
    }

    override suspend fun <ResponseBody, RequestBody> put(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?,
        requestBody: RequestBody?
    ): ResponseBody {
        throw UnsupportedOperationException(" ")
    }

    override suspend fun <ResponseBody> get(
        responseWrappedModel: Type,
        pathUrl: String,
        headers: Map<String, Any>?,
        queryParams: Map<String, Any>?
    ): ResponseBody {
        throw UnsupportedOperationException(" ")
    }


}