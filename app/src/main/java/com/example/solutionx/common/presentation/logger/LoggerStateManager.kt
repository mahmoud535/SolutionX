package com.example.solutionx.common.presentation.logger

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.solutionx.android.helpers.logging.Logger
import com.example.solutionx.android.helpers.logging.writers.LogcatWriter

class LoggerStateManager: ILoggerStateManager, LifecycleEventObserver {

    private val logger = LogcatWriter("mahmoud557", true)

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> logger.debug(String::class.java, "Application created")
            Lifecycle.Event.ON_START ->  logger.debug(String::class.java, "Application started")
            Lifecycle.Event.ON_RESUME -> logger.debug(String::class.java, "Application is opened")
            Lifecycle.Event.ON_PAUSE -> logger.debug(String::class.java, "Application is Paused")
            else -> Unit
        }
    }

    override fun attachLoggerToLifecycle(lifecycleOwner: LifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(this)
    }
}