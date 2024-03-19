package com.example.solutionx.features.login.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    @SerializedName("username")
    val username: String,
    val email: String?,
    val phoneNumber: String?,
)
