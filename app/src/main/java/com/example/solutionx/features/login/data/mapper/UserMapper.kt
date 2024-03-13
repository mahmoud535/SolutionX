package com.example.solutionx.features.login.data.mapper

import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.domain.model.User

class UserMapper {
    fun mapDtoToDomain(userEntity: UserEntity): User {
        return User(userEntity.id, userEntity.username, userEntity.email, userEntity.phoneNumber)
    }

    fun mapDomainToEntity(userDto: User): User {
        return User(userDto.id, userDto.username, userDto.email, userDto.phoneNumber)
    }
}