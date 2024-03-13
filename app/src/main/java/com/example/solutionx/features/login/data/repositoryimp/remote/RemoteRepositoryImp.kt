package com.example.solutionx.features.login.data.repositoryimp.remote

import com.example.solutionx.features.login.data.local.UserDatabase
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.remote.ServiceApi
import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(
    private val api: ServiceApi,
    private val localDataSource: UserDatabase,
    private val userMapper: UserMapper
) : RemoteRepo {
    override suspend fun loginWithEmail(email: String, password: String) =
        withContext(Dispatchers.IO) {
            api.loginWithEmail(email, password)

            val userDto = api.loginWithEmail(email, password)
            if (userDto.isSuccessful) {
                val loginResponse = userDto.body()
                val userDto = loginResponse
                val user = userMapper.mapDtoToDomain(userDto!!)
                localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
                return@withContext user
            } else {
                throw Exception("Login failed: ${userDto.message()}")
            }
        }

    override suspend fun loginWithPhone(phone: String, password: String) =
        withContext(Dispatchers.IO) {
            val response = api.loginWithPhone(phone, password)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                val userDto = loginResponse
                val user = userMapper.mapDtoToDomain(userDto!!)
                localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
                return@withContext user
            } else {
                throw Exception("Login with phone failed: ${response.message()}")
            }
        }

    override suspend fun loginWithSocial(token: String) = withContext(Dispatchers.IO) {
        val response = api.loginWithSocial(token)
        if (response.isSuccessful) {
            val loginResponse = response.body()
            val userDto = loginResponse
            val user = userMapper.mapDtoToDomain(userDto!!)
            localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
            return@withContext user
        } else {
            throw Exception("Login with social failed: ${response.message()}")
        }
    }
}