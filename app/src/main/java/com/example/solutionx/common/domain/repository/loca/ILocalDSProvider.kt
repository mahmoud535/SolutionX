package com.example.solutionx.common.domain.repository.loca

interface ILocalDSProvider {
    suspend fun <T> save(key: String, value: T)
    suspend fun <T> get(key: String): T?
}