package com.example.solutionx.features.login.data.mapper

import com.example.solutionx.features.login.data.model.UserDto
import com.example.solutionx.features.login.data.model.UserEntity
import com.example.solutionx.features.login.domain.model.User
import retrofit2.Response

object UserMapper {
    fun mapDtoToDomain(userDto: UserDto): User {
        return User(userDto.id, userDto.username, userDto.email, userDto.phoneNumber)
    }

    fun mapEntityToDomain(userEntity: UserEntity): User {
        return User(userEntity.id, userEntity.username, userEntity.email, userEntity.phoneNumber)
    }
    fun mapDomainToEntity(user: User): UserEntity {
        return UserEntity(user.id, user.username, user.email, user.phoneNumber)
    }
}


