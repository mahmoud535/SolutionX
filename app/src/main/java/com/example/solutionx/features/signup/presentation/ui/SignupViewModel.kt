package com.example.solutionx.features.signup.presentation.ui

import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.presentation.viewModel.BaseViewModel
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.presentation.ui.fragment.login.LoginIntent
import com.example.solutionx.features.login.presentation.ui.fragment.login.LoginState
import com.example.solutionx.features.signup.data.model.request.Phone
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.signup.domain.model.User
import com.example.solutionx.features.signup.domain.usecase.SignupUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUC: SignupUC,
) : BaseViewModel<SignUpState, SignUpIntent>() {
    override val initialState: SignUpState = SignUpState.Loading

    override fun handleIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.SignUp -> signUp(
                intent.firstName,
                intent.lastName,
                intent.email,
                intent.phoneNumber,
                intent.countryCode,
                intent.password
            )
        }
    }

    private fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        countryCode: String,
        password: String
    ) {
        viewModelScope.launch {
            val phone = Phone(
                countryCode = countryCode,
                number = phoneNumber
            )
//            val signupRequest = SignupRequest(
//                firstName = firstName,
//                lastName = lastName,
//                email = email,
//                phone = phone,
//                password = password,
//                passwordConfirmation = password
//            )
            val signupRequest = SignupRequest(
                firstName = "John",
                lastName = "Doe",
                email = "johndoe@example.com",
                phone = Phone(countryCode = "001", number = "1234567890"),
                password = "password123",
                passwordConfirmation = "password123"
            )
            signupUC.invoke(viewModelScope, signupRequest) { resource ->
                when (resource) {
                    is Resource.Progress -> _viewState.update { SignUpState.Loading }
                    is Resource.Success -> {
                        _viewState.update { SignUpState.Success(resource.model) }
                    }

                    is Resource.Failure -> _viewState.update {
                        SignUpState.Error(
                            resource.exception.message ?: "error in signup"
                        )
                    }
                }
            }
        }
    }
}