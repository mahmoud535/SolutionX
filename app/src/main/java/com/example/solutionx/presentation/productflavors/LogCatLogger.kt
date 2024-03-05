package com.example.solutionx.presentation.productflavors

import android.util.Log

class LogCatLogger:Logger {
    override fun log(message: String) {
       Log.d("LoggingFlavorsDemo", message)
    }
}