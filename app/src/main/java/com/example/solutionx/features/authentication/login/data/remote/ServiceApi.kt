package com.example.solutionx.features.authentication.login.data.remote

import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

internal interface ServiceApi {
    @POST("loginWithEmail")
    suspend fun loginWithEmail(@Query("email")email: String,@Query("password") password: String): LoginDto

    @POST("api/login")
    suspend fun loginWithPhone(@Body phone:Any): Response <LoginDto>

    @GET("loginWithSocial")
    suspend fun loginWithSocial(@Query("token") token: String): LoginDto
}