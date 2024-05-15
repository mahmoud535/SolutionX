package com.example.solutionx.common.domain.repository.usecase

import com.example.solutionx.common.data.mapper.ExceptionMapper
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/** crossinline keyword is used in function types to
 *  specify that a lambda passed as a parameter to the function
 *  cannot have non-local returns.**/
object BaseUseCase {
//    suspend inline fun <T> execute(
//        scope: CoroutineScope,
//        crossinline call: suspend () -> T,
//        crossinline onResult: (Resource<T>) -> Unit
//    ) {
//        scope.launch(Dispatchers.Main) {
//            onResult.invoke(Resource.loading())
//            try {
//                val result = withContext(Dispatchers.IO) {
//                    call()
//                }
//                onResult.invoke(Resource.success(result))
//
//                withContext(Dispatchers.Main){
//                    onResult.invoke(Resource.loading())
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.IO) {
//                    val failureResource = if (e is LeonException)
//                        e
//                    else
//                        LeonException.Unknown(message = "Unknown error: $e")
//
//                    onResult.invoke(Resource.failure(failureResource))
//                }
//
//                withContext(Dispatchers.Main) {
//                    onResult.invoke(Resource.loading(false))
//                }
//
//            }
//        }
//    }


    suspend inline fun <T> execute(
        scope: CoroutineScope,
        crossinline call: suspend () -> T,
        crossinline onResult: (Resource<T>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                val result = withContext(Dispatchers.IO) {
                    call()
                }
                onResult.invoke(Resource.success(result))
            } catch (e: LeonException.Network) {
                val failureResource = when (e) {
                    is LeonException.Network.Retrial -> Resource.failure<Nothing>(e)
                    is LeonException.Network.Unhandled -> Resource.failure(e)
                }
                onResult.invoke(failureResource)
            } catch (e: Exception) {
//                    val failureResource = if (e is LeonException)
//                        Resource.failure<Nothing>(e)
//                    else
//                        Resource.failure(LeonException.Unknown(message = "Unknown error: $e"))
//                    onResult.invoke(failureResource)

                when (e) {
                    is HttpException -> {
                        val mappedException = ExceptionMapper().map(e)
                        onResult.invoke(Resource.failure(mappedException))
                    }
                    is LeonException -> onResult.invoke(Resource.failure(e))
                    else -> {
                        val unknownException = LeonException.Unknown(message = "Unknown error: $e")
                        onResult.invoke(Resource.failure(unknownException))
                    }
                }
            }
        }
    }
}

