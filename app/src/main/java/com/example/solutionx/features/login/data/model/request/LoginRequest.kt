package com.example.solutionx.features.login.data.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("phone")
    val phone : Phone,
    @SerializedName("password")
    val password : String,
)
