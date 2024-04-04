package com.example.solutionx.features.login.data.repository.local

import com.example.solutionx.common.data.repository.local.StorageKeyEnum
import com.example.solutionx.common.domain.repository.loca.ILocalDSProvider
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

internal class LoginLocalDS (private val storageKV: ILocalDSProvider): ILoginLocalDS {
    override suspend fun saveUser(login: LoginEntity) {
        storageKV.save(StorageKeyEnum.ACCESS_TOKEN, login.token)
        storageKV.save(StorageKeyEnum.USER, Gson().toJson(login.user))
    }

    override suspend fun getAccessToken(): String {
        return storageKV.get(StorageKeyEnum.ACCESS_TOKEN, "")
    }

    override suspend fun getUser():UserEntity {
        val json = storageKV.get(StorageKeyEnum.USER, "")
        return Gson().fromJson(json, UserEntity::class.java)
    }

}