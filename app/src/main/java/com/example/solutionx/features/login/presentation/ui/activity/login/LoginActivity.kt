package com.example.solutionx.features.login.presentation.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.solutionx.databinding.ActivityLoginBinding
import com.example.solutionx.presentation.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val viewModelLanguage: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actions()

    }
    private fun actions() {
        listener()
        observeViewState()
        getLanguage()
    }


    private fun observeViewState() {
        GlobalScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    private fun renderState(state: LoginState) {
        when (state) {
            is LoginState.Success -> {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
            is LoginState.Loading -> {
                // Handle loading state if needed
            }
            is LoginState.Error -> {
                // Handle error state if needed
            }
        }
    }


    private fun listener() {
        binding.ivLanguages.setOnClickListener {
            showChangeLanguagesDialog()
        }
        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.etEmailClient.text.toString()
            val countryCode = binding.etCountruCode.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.processLoginIntent(LoginIntent.LoginWithPhone(phoneNumber,countryCode,password))
        }
    }

    private fun getLanguage() {
        viewModelLanguage.loadLocate(this)
    }

    private fun showChangeLanguagesDialog() {
        val listItems = arrayOf("عربي", "English")
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                viewModelLanguage.setLocate(this, "ar")
                recreate()
            } else if (which == 1) {
                viewModelLanguage.setLocate(this, "en")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

}