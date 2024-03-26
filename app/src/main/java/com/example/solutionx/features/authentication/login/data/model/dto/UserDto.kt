package com.example.solutionx.features.authentication.login.data.model.dto

import com.example.solutionx.features.authentication.login.domain.model.Phone
import com.example.solutionx.features.services.country.domain.models.Country
import com.google.gson.annotations.SerializedName

internal data class UserDto(
//    @SerializedName("id")
//    val id: Int? = null,
//    @SerializedName("name")
//    val name: String? = null,
//    @SerializedName("email")
//    val email: String? = null,
//    @SerializedName("phoneNumber")
//    val phoneNumber: String? = null,

    @SerializedName("all_permissions")
    val all_permissions: List<Any>? = null,
    @SerializedName("birthdate")
    val birthdate: Any? = null,
    @SerializedName("blocked")
    val blocked: Int? = null,
    @SerializedName("country")
    val country: Country? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("email_verified")
    val email_verified: Boolean? = null,
    @SerializedName("firstname")
    val firstname: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: Any? = null,
    @SerializedName("lastname")
    val lastname: String? = null,
    @SerializedName("middle_name")
    val middlename: String? = null,
    @SerializedName("phone")
    val phone: Phone? = null,
    @SerializedName("phone_verified")
    val phone_verified: Boolean? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("number")
    val number: String? = null,
)
