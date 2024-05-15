package com.example.solutionx.features.signup.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.solutionx.R
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.databinding.FragmentSignupBinding
import com.example.solutionx.features.login.presentation.ui.fragment.login.LoginState
import com.example.solutionx.features.login.presentation.ui.fragment.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

@AndroidEntryPoint
class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the click listener for the signup button
        binding.btnLogin.setOnClickListener {
            val firstName = binding.etFirstname.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhoneClient.text.toString()
            val countryCode = binding.etCountruCode.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.handleIntent(SignUpIntent.SignUp(firstName, lastName, email, phoneNumber, countryCode, password))
        }
        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        state()
    }

    fun state(){
        // Observe ViewModel state
        GlobalScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->
                    when (state) {
                        is SignUpState.Error -> {
//                            Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                        }
                        SignUpState.Loading -> {}
                        is SignUpState.Success -> {
//                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
                        }
                    }
                }
            }
        }
    }


}