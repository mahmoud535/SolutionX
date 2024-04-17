package com.example.solutionx.features.login.data.repository


import com.example.solutionx.features.login.data.mapper.LoginMapper
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.model.User
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

    override suspend fun saveUser(user: User) {
        val result = UserMapper.domainToEntity(user)
        localDs.saveUser(result)
    }

    override suspend fun saveAccessToken(token: String) =
        localDs.saveAccessToken(token)


    override suspend fun getUser(): UserEntity =
        localDs.getUser()



}