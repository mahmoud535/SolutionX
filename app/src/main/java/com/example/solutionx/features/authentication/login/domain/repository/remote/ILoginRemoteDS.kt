package com.example.solutionx.features.authentication.login.domain.repository.remote

import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto

internal interface ILoginRemoteDS {
//    suspend fun loginWithEmail(email: String, password: String): LoginDto
suspend fun loginWithPhone(countryCode: String,number: String,password: String):LoginDto

//    suspend fun loginWithSocial(token: String): LoginDto
}