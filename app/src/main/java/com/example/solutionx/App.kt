package com.example.solutionx

import android.app.Application
import com.example.solutionx.features.services.presentation.productflavors.helpers.logging.loggerprovider.LoggerProvider
import dagger.hilt.EntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}