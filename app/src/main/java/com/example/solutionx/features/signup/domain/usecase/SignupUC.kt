package com.example.solutionx.features.signup.domain.usecase

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.domain.repository.usecase.BaseUseCase
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.signup.domain.model.User
import com.example.solutionx.features.signup.domain.repository.ISignupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupUC @Inject constructor(
    private val repository: ISignupRepository,
) {
    operator fun invoke(scope: CoroutineScope, signupRequest: SignupRequest, onResult: (Resource<User>) -> Unit) {
        scope.launch(Dispatchers.Main) {
            BaseUseCase.execute(scope, call = {
                val result = repository.signupWithPhone(signupRequest)
                repository.saveUser(result.user)
                repository.saveAccessToken(result.token)
                repository.getUser()
                result.user
            }, onResult = onResult)
        }
    }

}