package com.example.solutionx

import android.app.Application
import dagger.hilt.EntryPoint
import dagger.hilt.android.HiltAndroidApp
import solutionx.android.helper.logging.LoggerProvider

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}