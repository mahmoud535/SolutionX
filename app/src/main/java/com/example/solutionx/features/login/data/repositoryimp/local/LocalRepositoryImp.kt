package com.example.solutionx.features.login.data.repositoryimp.local

import com.example.solutionx.features.login.data.local.UserDatabase
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.model.UserInfo
import com.example.solutionx.features.login.domain.repository.local.LocalRepo
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(private val db: UserDatabase, private val userMapper: UserMapper): LocalRepo {
    override suspend fun saveUser(userEntity: UserEntity) {
        db.userDao().saveUser(userMapper.mapDtoToDomain(userEntity))
    }

    override suspend fun getUser() {
        db.userDao().getUser()
    }
}