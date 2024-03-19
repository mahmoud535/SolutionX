package com.example.solutionx.features.login.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(
    val id: Int,
    val username: String,
    val email: String?,
    val phoneNumber: String?,
)
