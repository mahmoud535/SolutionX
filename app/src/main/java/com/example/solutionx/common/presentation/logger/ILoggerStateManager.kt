package com.example.solutionx.common.presentation.logger

import androidx.lifecycle.LifecycleOwner

interface ILoggerStateManager {
    fun attachLoggerToLifecycle(lifecycleOwner: LifecycleOwner)
}