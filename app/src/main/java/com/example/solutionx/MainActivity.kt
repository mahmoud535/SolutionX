package com.example.solutionx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import com.example.solutionx.BuildConfig


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Toast.makeText(this, "welcome ${BuildConfig.FLAVOR == "free"}", Toast.LENGTH_SHORT).show()
    }
}