package com.example.solutionx.common.domain.repository.loca.localds

interface ILocalDSProvider {
    suspend fun <Model> save(key: IStorageKeyEnum, data: Model)
    suspend fun <Model> get(key: IStorageKeyEnum, defaultValue: Model): Model
}