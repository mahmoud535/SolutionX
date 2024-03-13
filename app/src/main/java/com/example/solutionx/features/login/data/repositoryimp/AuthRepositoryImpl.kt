package com.example.solutionx.features.login.data.repositoryimp

//import com.example.solutionx.features.login.data.local.UserDatabase
//import com.example.solutionx.features.login.data.mapper.UserMapper
//import com.example.solutionx.features.login.data.remote.ServiceApi
//import com.example.solutionx.features.login.domain.model.User
//import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class AuthRepositoryImpl @Inject constructor (
//    private val remoteDataSource: ServiceApi,
//    private val localDataSource: UserDatabase,
//    private val userMapper: UserMapper
//) : RemoteRepo {
//    override suspend fun loginWithEmail(email: String, password: String): User {
//        val userDto = remoteDataSource.loginWithEmail(email, password)
//        if (userDto.isSuccessful) {
//            val loginResponse = userDto.body()
//            val userDto = loginResponse?.userInfo
//            val user = userMapper.mapDtoToDomain(userDto!!)
//            localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
//            return user
//        } else {
//            throw Exception("Login failed: ${userDto.message()}")
//        }
//    }
//
//    override suspend fun loginWithPhone(phone: String, password: String): User {
//        val response = remoteDataSource.loginWithPhone(phone, password)
//        if (response.isSuccessful) {
//            val loginResponse = response.body()
//            val userDto = loginResponse?.userInfo
//            val user = userMapper.mapDtoToDomain(userDto!!)
//            localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
//            return user
//        } else {
//            throw Exception("Login with phone failed: ${response.message()}")
//        }
//    }
//
//    override suspend fun loginWithSocial(token: String): User {
//        val response = remoteDataSource.loginWithSocial(token)
//        if (response.isSuccessful) {
//            val loginResponse = response.body()
//            val userDto = loginResponse?.userInfo
//            val user = userMapper.mapDtoToDomain(userDto!!)
//            localDataSource.userDao().saveUser(userMapper.mapDomainToEntity(user))
//            return user
//        } else {
//            throw Exception("Login with social failed: ${response.message()}")
//        }
//    }
//
//}