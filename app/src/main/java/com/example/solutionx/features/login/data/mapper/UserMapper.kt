package com.example.solutionx.features.login.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.login.data.model.dto.UserDto
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.domain.model.User

internal object UserMapper : Mapper<UserDto, User, UserEntity>() {

    override fun dtoToDomain(model: UserDto): User {
        return User(
            id = model.id ?: 0,
            name = model.firstname.orEmpty(),
            email = model.email.orEmpty(),
        )
    }

    override fun domainToEntity(model: User): UserEntity {
        return UserEntity(
            id = model?.id ?: 0,
            name = model?.name.orEmpty(),
            email = model?.email.orEmpty(),
        )
    }
}


