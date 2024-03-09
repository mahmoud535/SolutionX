package com.example.solutionx.presentation.productflavors

import com.example.solutionx.BuildConfig

object LoggerProvider {
    fun getLogger(): Logger {
        return when (BuildConfig.FLAVOR) {
            "logCat" -> LogCatLogger()
            "logWriter" -> FileLogger()
            else -> NullLogger()
        }
    }
}