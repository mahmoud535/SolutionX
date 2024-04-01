package com.example.solutionx.common.domain.repository.loca

interface ILocalDSProvider {
    suspend fun <local> save(key: String, value: local)
    suspend fun <local> get(key: String): local?
}