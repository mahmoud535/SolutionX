package com.example.solutionx.features.signup.domain.repository.remote

import com.example.solutionx.features.signup.data.model.dto.SignupDto
import com.example.solutionx.features.signup.data.model.request.SignupRequest

internal interface ISignupRemoteDS {
    suspend fun signupWithPhone(signupRequest: SignupRequest): SignupDto?
}