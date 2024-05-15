package com.example.solutionx.features.savelist.domain.repository.local

interface ILocalDataSource {
    suspend fun saveList(list: List<String>)
    suspend fun getList(): List<String>
}