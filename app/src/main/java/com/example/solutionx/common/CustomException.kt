package com.example.solutionx.common

import java.io.IOException
import kotlin.Exception

sealed class CustomException (message: String? = null, cause: Throwable? = null) : Exception(message, cause) {
    class UserException(message: String? = null, cause: Throwable? = null) : CustomException(message, cause)
    class DatabaseException(message: String? = null, cause: Throwable? = null) : CustomException(message, cause)
    class ApiException(message: String? = null, cause: IOException? = null) : CustomException(message, cause)
}