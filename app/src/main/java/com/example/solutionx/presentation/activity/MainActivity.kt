package com.example.solutionx.presentation.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.solutionx.BuildConfig
import com.example.solutionx.R
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Locale

//import com.example.solutionx.presentation.activity.CustomLogger
import java.util.Date
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    var flavor: String ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Toast.makeText(this, "welcome ${BuildConfig.FLAVOR == "free"}", Toast.LENGTH_SHORT).show()

        fun log(message:String){
            when (flavor){
                "logCat" -> logToLogCat(message)
                "logWriter" -> logToFile(message)
                "production" -> { /* Do nothing for production flavor */ }
                else -> Log.e(TAG, "Invalid flavor: $flavor")
            }
        }

        log("This is a log message.")

    }

    fun logToLogCat(message: String) {
        Log.d(TAG, message)
       Log.d("MyApp", message)
    }

    private fun logToFile(message: String) {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "log_$timeStamp.txt"
        val directory = File("/storage/emulated/0/Download/SolutionX")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val file = File(directory, fileName)
        try {
            FileWriter(file, true).use { writer ->
                writer.append("$message\n")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error writing to file: ${e.message}")
        }

//        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SolutionX")
//        if (!directory.exists()) {
//            directory.mkdirs()
//        }
//        val file = File(directory, "log.txt")
//        FileWriter(file, true).use {
//            it.write("$message\n")
//        }
    }

    companion object {
        private const val TAG = "CustomLogger"
    }

//    val logger = CustomLogger(BuildConfig.FLAVOR)
//    logger.log("This is a log message.")
}