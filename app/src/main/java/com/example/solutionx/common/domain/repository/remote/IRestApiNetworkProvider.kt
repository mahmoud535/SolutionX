package com.example.solutionx.common.domain.repository.remote

internal interface IRestApiNetworkProvider {

    suspend fun <response> get(url: String, query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): response
    suspend fun <response> post( body: Map<String, Any> = emptyMap()): response

    //the parameter in method put is the correct one
    suspend fun <response> put(url: String, body: Map<String, Any> = emptyMap(), query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): response
    suspend fun <response> delete(url: String, query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): response

}