package com.example.solutionx.features.signup.data.repository


import com.example.solutionx.features.login.data.mapper.LoginMapper
import com.example.solutionx.features.signup.data.model.entity.UserEntity
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.model.signup.Signup
import com.example.solutionx.features.signup.data.mapper.SignupMapper
import com.example.solutionx.features.signup.data.mapper.UserMapper
import com.example.solutionx.features.signup.domain.model.User
import com.example.solutionx.features.signup.domain.repository.ISignupRepository
import com.example.solutionx.features.signup.domain.repository.local.ISignupLocalDS
import com.example.solutionx.features.signup.domain.repository.remote.ISignupRemoteDS


internal class SignupRepository  (
    private val remoteDs: ISignupRemoteDS,
    private val localDs: ISignupLocalDS,
) : ISignupRepository {

    override suspend fun signupWithPhone(signupRequest: SignupRequest): Signup {
        val result = remoteDs.signupWithPhone(signupRequest)
        return SignupMapper.dtoToDomain(result!!)
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