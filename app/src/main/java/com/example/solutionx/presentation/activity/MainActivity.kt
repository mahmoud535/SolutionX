package com.example.solutionx.presentation.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.solutionx.BuildConfig
import com.example.solutionx.R
import com.example.solutionx.presentation.productflavors.LoggerProvider
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Locale

//import com.example.solutionx.presentation.activity.CustomLogger
import java.util.Date
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logger = LoggerProvider.getLogger(BuildConfig.FLAVOR)
        logger.log("Hello from ${BuildConfig.FLAVOR} flavor!")
    }

}