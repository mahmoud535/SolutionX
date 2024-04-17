package com.example.solutionx.features.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class LoginWithEmailUC  @Inject constructor(private val repository: ILoginRepository){
    operator fun invoke(scope:CoroutineScope, email: String, password: String, onResult: (Resource<Login>) -> Unit){
//        scope.launch(Dispatchers.Main) {
//            onResult.invoke(Resource.loading())
//            try {
//                withContext(Dispatchers.IO){
//                    val result = repository.loginWithEmail(email, password)
//                      repository.saveUser(result)
//                    onResult.invoke(Resource.success(result))
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
//            BaseUseCase.execute(scope, call = {
//                val result = repository.loginWithEmail(email, password)
//                repository.saveUser(result)
//                result
//            }, onResult = onResult)
        }
    }



}