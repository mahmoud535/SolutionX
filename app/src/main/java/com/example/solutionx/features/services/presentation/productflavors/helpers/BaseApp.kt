package com.example.solutionx.features.services.presentation.productflavors.helpers

import android.app.Application
import com.example.solutionx.features.services.presentation.productflavors.helpers.logging.loggerprovider.LoggerProvider

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        LoggerProvider.provideLogger()
    }
}