package com.example.solutionx

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import solutionx.android.helper.logging.LoggerProvider
import androidx.work.Configuration
import androidx.work.WorkManager
import javax.inject.Inject
import kotlin.math.log

@HiltAndroidApp
class App:Application() , Configuration.Provider{
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()



}