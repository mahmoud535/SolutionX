package com.example.solutionx.features.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.domain.BaseUseCase
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginWithPhoneUC  @Inject constructor(
    private val repository: ILoginRepository,
) {
    operator fun invoke(scope: CoroutineScope, loginRequest: LoginRequest, onResult: (Resource<User>) -> Unit) {
//        scope.launch(Dispatchers.Main) {
//            onResult.invoke(Resource.loading())
//            try {
//                withContext(Dispatchers.IO){
//                    val result = repository.loginWithPhone(loginRequest)
////                    val token = result.accessToken
//                      repository.saveUser(result)
//                    Log.e("Mahmoud",repository.getUser().toString())
//                    Log.e("Mahmoud",repository.getAccessToken().toString())
//                    onResult.invoke(Resource.success(result.user))
//                }
//                withContext(Dispatchers.Main){
//                    onResult.invoke(Resource.loading())
//                }
//            }catch (e: Exception){
//                withContext(Dispatchers.IO){
//                    val failureResource = if (e  is LeonException)
//                        e
//                    else
//                        LeonException.Unknown(message = "Unknown error: $e")
//
//                    onResult.invoke(Resource.failure(failureResource))
//                }
//
//                withContext(Dispatchers.Main){
//                    onResult.invoke(Resource.loading(false))
//                }
//            }
//        }

        scope.launch(Dispatchers.Main) {
            BaseUseCase.execute(scope, call = {
                val result = repository.loginWithPhone(loginRequest)
                repository.saveUser(result)
//                    val token = result.accessToken
                result.user
            }, onResult = onResult)
        }
    }
}