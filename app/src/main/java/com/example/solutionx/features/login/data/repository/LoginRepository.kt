package com.example.solutionx.features.login.data.repository


import com.example.solutionx.features.login.data.mapper.LoginMapper
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import kotlinx.coroutines.flow.Flow


internal class LoginRepository  (
    private val remoteDs: ILoginRemoteDS,
    private val localDs: ILoginLocalDS,
) : ILoginRepository {

    override suspend fun loginWithPhone(loginRequest: LoginRequest): Login {
        val result = remoteDs.loginWithPhone(loginRequest)
        return LoginMapper.dtoToDomain(result!!)
    }

    override suspend fun saveUser(login: Login) {
        val result = LoginMapper.domainToEntity(login)
        localDs.saveUser(result)
    }

//    override suspend fun getAccessToken(): Flow<String?> {
//        return localDs.getAccessToken()
//    }
//
//    override suspend fun getUser():UserEntity =
//          localDs.getUser()
//


}