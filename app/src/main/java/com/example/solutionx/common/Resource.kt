package com.example.solutionx.common


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
//    class Loading <T>(data: T? = null): Resource<T>(data)
//    class Success<T>(data: T) : Resource<T>(data)
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    class Loading<T>(val loading: Boolean) : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data = data)
    class Failure<T>(val exception: CustomException) : Resource<T>(message = exception.message)

}