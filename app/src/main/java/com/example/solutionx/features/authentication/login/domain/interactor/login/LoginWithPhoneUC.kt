package com.example.solutionx.features.authentication.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.domain.BaseUseCase
import com.example.solutionx.features.authentication.login.domain.model.Login
import com.example.solutionx.features.authentication.login.domain.model.LoginRequest
import com.example.solutionx.features.authentication.login.domain.model.User
import com.example.solutionx.features.authentication.login.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginWithPhoneUC  @Inject constructor(
    private val repository: ILoginRepository,
) {
    operator fun invoke(scope: CoroutineScope, loginRequest: LoginRequest, onResult: (Resource<User>) -> Unit){

        scope.launch(Dispatchers.Main) {
            BaseUseCase.execute(scope, call = {
                val result = repository.loginWithPhone(
                    loginRequest.countryCode,
                    loginRequest.phoneNumber,
                    loginRequest.password)
                repository.saveUser(result)
                result.user
            }, onResult = onResult)
        }
    }
}