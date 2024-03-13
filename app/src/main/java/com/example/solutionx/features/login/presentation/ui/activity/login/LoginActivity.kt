package com.example.solutionx.features.login.presentation.ui.activity.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.solutionx.databinding.ActivityLoginBinding
import com.example.solutionx.features.singleclick.presentation.ui.activity.list.ListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels()
    private val viewModelLanguage: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModelLanguage.loadLocate()
//
//        lifecycleScope.launch {
//            viewModelLanguage.languageChanged.collect {
//                    recreate()
//            }
//        }

        loadLocate()
        listener()
        observeViewState()


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

    private fun renderState(state: LoginViewState) {
        when (state) {
            is LoginViewState.UserLoggedIn -> {

            }

            LoginViewState.Loading -> {

            }

            is LoginViewState.Error -> {
//                Toast.makeText(this@LoginActivity, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun listener() {
        binding.ivLanguages.setOnClickListener {
            showChangeLanguagesDialog()
//            viewModelLanguage.showChangeLanguagesDialog(this@LoginActivity)
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailClient.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.processIntent(LoginIntent.LoginWithEmail(email, password))

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showChangeLanguagesDialog() {
        val listItmes = arrayOf("عربي", "English")
        val mBuilder = AlertDialog.Builder(this@LoginActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
            if (which == 0) {
                setLocate("ar")
                recreate()
            } else if (which == 1) {
                setLocate("en")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language!!)
    }
}