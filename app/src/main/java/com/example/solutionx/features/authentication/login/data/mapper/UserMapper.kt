package com.example.solutionx.features.authentication.login.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.authentication.login.data.model.dto.UserDto
import com.example.solutionx.features.authentication.login.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.login.domain.model.User

internal object UserMapper : Mapper<UserDto, User, UserEntity>() {

    override fun dtoToDomain(model: UserDto): User {
        return User(
            id = model.id ?: -1,
            username = model.username.orEmpty(),
            email = model.email.orEmpty(),
            password = model.password.orEmpty(),
            age = model.age ?: 0
        )
    }

    override fun domainToEntity(model: User): UserEntity {
        return UserEntity(
            id = model.id ,
            username = model.username,
            email = model.email,
            password = model.password,
            age = model.age
        )
    }
}


