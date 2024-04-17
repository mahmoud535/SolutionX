package solutionx.android.helper.logging

import com.example.solutionx.BuildConfig
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.android.helpers.logging.writers.LogcatWriter

object LoggerProvider {
    fun provideLogger(tagKey: String = "SolutionX") {
        LoggerFactory.setLogWriter(LogcatWriter(tagKey, BuildConfig.DEBUG))
    }
}