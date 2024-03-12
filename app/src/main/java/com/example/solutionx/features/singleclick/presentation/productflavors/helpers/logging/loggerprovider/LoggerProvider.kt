package com.example.solutionx.features.singleclick.presentation.productflavors.helpers.logging.loggerprovider

import am.leon.solutionx.android.helpers.logging.LoggerFactory
import am.leon.solutionx.android.helpers.logging.writers.LogcatWriter
import com.intuit.sdp.BuildConfig

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX-Full") {
        LoggerFactory.setLogWriter(LogcatWriter(tagKey, BuildConfig.DEBUG))
    }
}