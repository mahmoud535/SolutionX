package com.example.solutionx.features.login.data.repositoryimp.remote

import com.example.solutionx.features.login.data.local.UserDatabase
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.data.remote.ServiceApi
import com.example.solutionx.features.login.domain.model.UserInfo
import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor() : RemoteRepo {
    override suspend fun loginWithEmail(email: String, password: String): UserInfo {
        return UserInfo("phoneAccessToken", UserDto(5, "phoneUsername","email",""))

    }
    override suspend fun loginWithPhone(phone: String, password: String): UserInfo {
        return UserInfo("phoneAccessToken", UserDto(5, "phoneUsername","email",""))

    }
    override suspend fun loginWithSocial(token: String): UserInfo {
        return UserInfo("phoneAccessToken", UserDto(5, "phoneUsername","email",""))
    }


}