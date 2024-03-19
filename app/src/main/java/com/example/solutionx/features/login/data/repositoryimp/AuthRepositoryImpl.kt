package com.example.solutionx.features.login.data.repositoryimp


import com.example.solutionx.features.login.data.local.UserDao
import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.data.remote.ServiceApi
import com.example.solutionx.features.login.domain.repository.LoginRepository
import com.example.solutionx.features.login.domain.repository.local.LocalRepo
import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


class AuthRepositoryImpl @Inject constructor (
    private val remoteDataSource: ServiceApi,
    private val localDataSource: UserDao,
) : LoginRepository {

    override suspend fun loginWithEmail(email: String, password: String):Response<UserDto>{
      return  remoteDataSource.loginWithEmail(email, password)
    }
    override suspend fun loginWithPhone(phone: String, password: String) :Response<UserDto>{
      return  remoteDataSource.loginWithPhone(phone, password)
    }
    override suspend fun loginWithSocial(token: String): Response<UserDto> {
        return remoteDataSource.loginWithSocial(token)
    }
    override suspend fun saveUser(user: UserEntity) {
        localDataSource.saveUser(user)
    }
    override suspend fun getUser() {
        localDataSource.getUser()
    }

}