package com.example.solutionx.features.authentication.login.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class LoginDto (
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("user")
    val user: UserDto? = null


)