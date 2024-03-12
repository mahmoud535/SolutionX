package com.example.solutionx.features.login.data.mapper

import com.example.solutionx.features.login.domain.model.User

class UserMapper {
    fun mapDtoToDomain(dto: User): User {
        return User(dto.id, dto.username, dto.email, dto.phoneNumber)
    }

    fun mapDomainToEntity(user: User): User {
        return User(user.id, user.username, user.email, user.phoneNumber)
    }
}