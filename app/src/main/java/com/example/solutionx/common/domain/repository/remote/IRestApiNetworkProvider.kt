package com.example.solutionx.common.domain.repository.remote

internal interface IRestApiNetworkProvider {

    suspend fun <T> get(url: String, query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): T
    suspend fun <T> post( body: Map<String, Any> = emptyMap()): T
    suspend fun <T> put(url: String, body: Map<String, Any> = emptyMap(), query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): T
    suspend fun <T> delete(url: String, query: Map<String, Any> = emptyMap(), headers: Map<String, String> = emptyMap()): T

}