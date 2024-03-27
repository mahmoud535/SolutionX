package com.example.solutionx.features.authentication.login.domain.model

import android.os.Parcelable
import com.example.solutionx.features.authentication.login.data.model.dto.UserDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class Login(
    val token: String,
    val user: User,
)
