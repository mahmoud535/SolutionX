package com.example.solutionx.features.savelist.data.repository.local

import com.example.solutionx.common.data.repository.local.localds.StorageKeyEnum
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.features.savelist.domain.repository.local.ILocalDataSource
import javax.inject.Inject

class LocalDataSource (private val localDSProvider: ILocalDSProvider) :
    ILocalDataSource {
    override suspend fun saveList(list: List<String>) {
        localDSProvider.save(StorageKeyEnum.NAMES, list.joinToString())
    }

    override suspend fun getList(): List<String> {
//        return localDSProvider.get(StorageKeyEnum.NAMES, "").split(" ")
        val data = localDSProvider.get(StorageKeyEnum.NAMES, "")
        return data.split(" ")
    }
}