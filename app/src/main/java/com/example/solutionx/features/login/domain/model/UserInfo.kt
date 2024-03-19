package com.example.solutionx.features.login.domain.model

import com.example.solutionx.features.login.data.model.UserDto

data class UserInfo(
    var accessToken: String,
    var userInfo: UserDto
)
