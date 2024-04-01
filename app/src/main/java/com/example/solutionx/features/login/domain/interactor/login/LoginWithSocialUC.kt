package com.example.solutionx.features.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginWithSocialUC  @Inject constructor(
    private val repository: ILoginRepository,
) {
    operator fun invoke(scope: CoroutineScope, token: String, onResult: (Resource<Login>) -> Unit){
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO){
//                    val result = repository.loginWithSocial(token)
//                    repository.saveUser(result)
//                    onResult.invoke(Resource.success(result))
                }
                withContext(Dispatchers.Main){
                    onResult.invoke(Resource.loading())
                }
            }catch (e: Exception){
                withContext(Dispatchers.IO){
                    val failureResource = if (e  is LeonException)
                        e
                    else
                        LeonException.Unknown(message = "Unknown error: $e")

                    onResult.invoke(Resource.failure(failureResource))
                }

                withContext(Dispatchers.Main){
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }
}