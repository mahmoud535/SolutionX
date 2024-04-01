package com.example.solutionx.common.data.repository.remote

import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.domain.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

internal interface ServiceApi {
//    @POST("loginWithEmail")
//    suspend fun loginWithEmail(@Query("email")email: String,@Query("password") password: String): LoginDto

    @POST("login")
    suspend fun loginWithPhone(@Body loginRequest: LoginRequest): Response<LoginDto>

//    @GET("loginWithSocial")
//    suspend fun loginWithSocial(@Query("token") token: String): LoginDto
}