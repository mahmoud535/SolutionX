package com.example.solutionx.features.login.data.model.dto


import com.google.gson.annotations.SerializedName

 data class CountryDto(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone_code")
    val phoneCode: String? = null
)