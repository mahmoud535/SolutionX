package com.example.solutionx.features.login.presentation.ui.fragment.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.solutionx.R
import com.example.solutionx.android.helpers.logging.writers.LogcatWriter
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.presentation.fragment.BaseFragment
import com.example.solutionx.common.presentation.logger.ILoggerStateManager
import com.example.solutionx.common.presentation.logger.LoggerStateManager
import com.example.solutionx.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) , ILoggerStateManager by LoggerStateManager() {


    private val viewModel: LoginViewModel by viewModels()
    private val viewModelLanguage: LanguageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        attachLoggerToLifecycle(viewLifecycleOwner)
        setUpActions()
        return binding.root
    }

    override fun setUpActions() {
        getLanguage()
        setUpObservers()
        setUpListeners()
        setUpRecyclerView()
    }

    override fun setUpObservers() {
        GlobalScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    renderState(state)
                }
            }
        }
    }

    override fun setUpListeners() {
        binding.ivLanguages.setOnClickListener {
            showChangeLanguagesDialog()
        }
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        if (validateLoginDetails()) {
            val phoneNumber = binding.etEmailClient.text.toString()
            val countryCode = binding.etCountruCode.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.handleIntent(
                LoginIntent.LoginWithPhone(phoneNumber, countryCode, password)
            )
            gotoMainFragment()
        }
    }

    private fun gotoMainFragment(){
        val actions =
            LoginFragmentDirections.actionLoginFragmentToMainFragment()
        view?.findNavController()?.navigate(actions)
    }

    override fun setUpRecyclerView() {
        setSwipeRefreshEnabled(true)
    }

    override fun onRefresh() {
        setRefreshing(false)
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etEmailClient.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(binding.etPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            TextUtils.isEmpty(binding.etCountruCode.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_countrycode), true)
                false
            }

            else -> {
                true
            }
        }
    }

    private fun renderState(state: LoginState) {
        when (state) {
            is LoginState.Success -> {
                hideProgressBar()
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
            is LoginState.Loading -> showProgressBar(resources.getString(R.string.please_wait))
            is LoginState.Error -> {
                hideProgressBar()
                handleException(LeonException.Unknown(state.message))
            }
        }
    }


    private fun getLanguage() {
        viewModelLanguage.loadLocate(requireContext())
    }

    private fun showChangeLanguagesDialog() {
        val listItems = arrayOf("عربي", "English")
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                viewModelLanguage.setLocate(requireContext(), "ar")
            } else if (which == 1) {
                viewModelLanguage.setLocate(requireContext(), "en")
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }


}