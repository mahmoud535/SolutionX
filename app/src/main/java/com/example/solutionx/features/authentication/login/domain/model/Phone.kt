package com.example.solutionx.features.authentication.login.domain.model

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("country_code")
    val country_code: String? = null,
    @SerializedName("extension")
    val extension: Any? = null,
    @SerializedName("holder_name")
    val holder_name: Any? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("number")
    val number: String? = null,
    @SerializedName("type")
    val type: Any? = null,
)