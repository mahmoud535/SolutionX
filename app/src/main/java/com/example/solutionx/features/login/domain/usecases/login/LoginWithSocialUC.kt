package com.example.solutionx.features.login.domain.usecases.login

import com.example.solutionx.common.CustomException
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.LoginRepository
import com.example.solutionx.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginWithSocialUC @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(token: String): Flow<Resource<User>> = flow {
        try {
            val response = loginRepository.loginWithSocial(token)
            if (response.isSuccessful) {
                val userDto: UserDto? = response.body()
                val user = UserMapper.mapDtoToDomain(userDto!!)
                loginRepository.saveUser(UserMapper.mapDomainToEntity(user))
                emit(Resource.Success(user))
            }
        } catch (e: CustomException.UserException) {
            emit(Resource.Failure(CustomException.UserException("Login failed: ${e.message}")))
        } catch (e: CustomException.ApiException) {
            emit(Resource.Failure(CustomException.ApiException("Error in IO Exception: ${e.message}")))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}