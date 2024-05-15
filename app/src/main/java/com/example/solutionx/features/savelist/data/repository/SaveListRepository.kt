package com.example.solutionx.features.savelist.data.repository

import com.example.solutionx.features.savelist.domain.repository.ISaveListRepository
import com.example.solutionx.features.savelist.domain.repository.local.ILocalDataSource
import javax.inject.Inject

class SaveListRepository (
    private val localDataSource: ILocalDataSource
) : ISaveListRepository {
    override suspend fun saveList(list: List<String>) {
        localDataSource.saveList(list)
    }

    override suspend fun getList(): List<String> {
        return localDataSource.getList()
    }


}