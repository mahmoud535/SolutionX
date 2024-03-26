package com.example.solutionx.features.authentication.login.data.model.entity

import com.example.solutionx.features.authentication.login.domain.model.Phone


data class UserEntity(
    val email: String,
    val id: Int,
    val lastname: String,
    val middlename: String,
    val phone: Phone,
    val phone_verified: Boolean,
    val username: String,
    val number: String,
)
