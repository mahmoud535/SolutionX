package com.example.solutionx.features.signup.data.model.request

import com.example.solutionx.features.signup.domain.model.User
import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone : Phone,
    @SerializedName("password")
    val password : String,
    @SerializedName("password_confirmation")
    val passwordConfirmation : String,
)
