package com.example.solutionx.features.login.data.repositoryimp.local

import com.example.solutionx.features.login.data.local.UserDao
import com.example.solutionx.features.login.data.local.UserDatabase
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.domain.repository.local.LocalRepo
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(private val db: UserDao): LocalRepo {
    override suspend fun saveUser(userEntity: UserEntity) {
//        db.saveUser(UserMapper.mapEntityToDomain(userEntity))
    }
    override suspend fun getUser() {
        db.getUser()
    }
}