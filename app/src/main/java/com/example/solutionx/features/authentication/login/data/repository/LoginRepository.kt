package com.example.solutionx.features.authentication.login.data.repository


import com.example.solutionx.features.authentication.login.data.mapper.LoginMapper
import com.example.solutionx.features.authentication.login.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.login.domain.model.Login
import com.example.solutionx.features.authentication.login.domain.model.User
import com.example.solutionx.features.authentication.login.domain.repository.ILoginRepository
import com.example.solutionx.features.authentication.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.authentication.login.domain.repository.remote.ILoginRemoteDS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



internal class LoginRepository  @Inject constructor(
    private val remoteDs: ILoginRemoteDS,
    private val localDs: ILoginLocalDS,
) : ILoginRepository {



    override suspend fun loginWithPhone(countryCode: String,
                                        phone: String,
                                        password: String): Login {
       val result = remoteDs.loginWithPhone(countryCode,phone,password)
        return LoginMapper.dtoToDomain(result)
    }



    override suspend fun saveUser(login: Login) {
        val result = LoginMapper.domainToEntity(login)
        localDs.saveUser(result)
    }

    override suspend fun getAccessToken(): Flow<String?> {
        return localDs.getAccessToken()
    }

    override suspend fun getUser() {
          localDs.getUser()
    }


}