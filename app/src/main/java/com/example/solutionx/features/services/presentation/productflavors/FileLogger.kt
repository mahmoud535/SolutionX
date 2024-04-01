package com.example.solutionx.features.services.presentation.productflavors

import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter

class FileLogger: Logger {
    override fun log(message: String) {
        val fileName = "logging.txt"
        val directory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "SolutionX")

        if (!directory.exists()) {
            directory.mkdirs()
        }

        val file = File(directory, fileName)

        val printWriter = PrintWriter(FileOutputStream(file, true))
        printWriter.println(message)
        printWriter.flush()
        printWriter.close()

        println("Writing to file: $message")
    }
}