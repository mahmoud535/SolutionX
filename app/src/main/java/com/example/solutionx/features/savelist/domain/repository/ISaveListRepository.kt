package com.example.solutionx.features.savelist.domain.repository

interface ISaveListRepository {
    suspend fun saveList(list: List<String>)
    suspend fun getList(): List<String>
}