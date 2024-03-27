package com.example.solutionx.features.authentication.login.presentation.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.solutionx.databinding.ActivityLoginBinding
import com.example.solutionx.presentation.ui.activity.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val viewModelLanguage: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe access token and user data
//        observeUserPreferences()
        actions()



    }

//    private fun observeUserPreferences() {
//        viewModel.accessToken.observe(this) { accessToken ->
//            if (!accessToken.isNullOrEmpty()) {
//                // Access token is saved
//                // Do something, such as navigating to the main activity
//                startActivity(Intent(this, MainActivity::class.java))
//                finish() // Optional: finish the current activity
//            } else {
//                Toast.makeText(this@LoginActivity, "token not saved ", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Similarly, you can observe the user data here if you have a method for it in UserPreferences
//        // viewModel.userData.observe(this) { userData ->
//        //     // Handle user data changes
//        // }
//    }

    private fun actions() {
        listener()
        observeViewState()
        getLanguage()
    }


    private fun observeViewState() {
        GlobalScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
//                    renderState(state)
                }
            }
        }
    }

//    private fun renderState(state: LoginState) {
//        when (state) {
//            is LoginState.UserLoggedIn -> {
//                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                finish()
//            }
//
//           is LoginState.Loading -> {
//
//            }
//
//            is LoginState.Error -> {
//                GlobalScope.launch(Dispatchers.Main) {
//                    Toast.makeText(this@LoginActivity, state.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }


    private fun listener() {
        binding.ivLanguages.setOnClickListener {
            showChangeLanguagesDialog()
        }
        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.etEmailClient.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.processLoginIntent(LoginIntent.LoginWithPhone(phoneNumber," ",password))
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