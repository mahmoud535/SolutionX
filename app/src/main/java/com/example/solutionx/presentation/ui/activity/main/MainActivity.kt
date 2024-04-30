package com.example.solutionx.presentation.ui.activity.main

import android.content.Context
import com.example.solutionx.android.helpers.logging.getClassLogger
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.solutionx.BuildConfig
import com.example.solutionx.R
import com.example.solutionx.common.presentation.activity.BaseActivity
import com.example.solutionx.databinding.ActivityMainBinding
import com.example.solutionx.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

        val navController = navHostFragment?.navController
    }

}