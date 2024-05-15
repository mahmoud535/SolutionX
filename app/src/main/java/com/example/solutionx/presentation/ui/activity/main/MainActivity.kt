package com.example.solutionx.presentation.ui.activity.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.solutionx.common.presentation.activity.BaseActivity
import com.example.solutionx.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as? NavHostFragment

        val navController = navHostFragment?.navController
    }

}