package com.example.solutionx.presentation.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logger = LoggerProvider.getLogger()
        logger.log("Hello from ${BuildConfig.FLAVOR} flavor!")
    }

}