package com.example.solutionx.features.services.presentation.productflavors.helpers.logging.loggerprovider

import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.android.helpers.logging.writers.LogcatWriter
import com.intuit.sdp.BuildConfig

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(LogcatWriter(tagKey, BuildConfig.DEBUG))
    }
}