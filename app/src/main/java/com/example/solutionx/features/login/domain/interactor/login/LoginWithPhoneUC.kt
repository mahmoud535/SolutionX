package com.example.solutionx.features.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.domain.repository.usecase.BaseUseCase
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginWithPhoneUC  @Inject constructor(
    private val repository: ILoginRepository,
) {
    operator fun invoke(scope: CoroutineScope, loginRequest: LoginRequest, onResult: (Resource<User>) -> Unit) {
        scope.launch(Dispatchers.Main) {
            BaseUseCase.execute(scope, call = {
                val result = repository.loginWithPhone(loginRequest)
                repository.saveUser(result.userInfo)
                repository.saveAccessToken(result.accessToken)
                repository.getUser()
                result.userInfo
            }, onResult = onResult)
        }
    }
}