package com.example.solutionx.features.login.presentation.ui.fragment.login

import android.os.Bundle
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
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.presentation.fragment.BaseFragment
import com.example.solutionx.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding:: inflate
) {



    private val viewModel: LoginViewModel by viewModels()
    private val viewModelLanguage: LanguageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        doubleBackToExit()

        return binding.root
    }

    override fun setUpActions() {
        getLanguage()
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
            val phoneNumber = binding.etEmailClient.text.toString()
            val countryCode = binding.etCountruCode.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.handleIntent(LoginIntent.LoginWithPhone(phoneNumber,countryCode,password))

            val actions =
                LoginFragmentDirections.actionLoginFragmentToMainFragment()
            view?.findNavController()?.navigate(actions)
        }
    }

    override fun setUpRecyclerView() {
        setSwipeRefreshEnabled(true)
    }

    override fun onRefresh() {

    }

    private fun renderState(state: LoginState) {
        when (state) {
            is LoginState.Success -> {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
            is LoginState.Loading -> {
                showProgressBar("please wait")
            }
            is LoginState.Error -> {
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
                fragmentManager?.beginTransaction()?.apply {
                    detach(this@LoginFragment)
                    attach(this@LoginFragment)
                    commit()
                }
            } else if (which == 1) {
                viewModelLanguage.setLocate( requireContext(), "en")
                fragmentManager?.beginTransaction()?.apply {
                    detach(this@LoginFragment)
                    attach(this@LoginFragment)
                    commit()
                }
            }
            dialog.dismiss()
        }
        val mDialog = mBuilder.create()
        mDialog.show()
    }


}