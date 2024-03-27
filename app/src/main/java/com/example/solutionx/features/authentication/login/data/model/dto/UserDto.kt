package com.example.solutionx.features.authentication.login.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class UserDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("age")
    val age: Int? = null,
)
