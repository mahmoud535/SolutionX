package com.example.solutionx.presentation.productflavors

object LoggerProvider {
    fun getLogger(flavor: String): Logger {
        return when (flavor) {
            "logCat" -> LogCatLogger()
            "logWriter" -> FileLogger()
            else -> NullLogger()
        }
    }
}