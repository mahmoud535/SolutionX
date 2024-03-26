package com.example.solutionx.features.authentication.login.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.authentication.login.data.model.dto.UserDto
import com.example.solutionx.features.authentication.login.data.model.entity.UserEntity
import com.example.solutionx.features.authentication.login.domain.model.User

internal object UserMapper : Mapper<UserDto, User, UserEntity>() {

    override fun dtoToDomain(model: UserDto): User {
        return User(
            id = model.id ?: -1,
            email = model.email.orEmpty(),
            lastname = model.lastname.orEmpty(),
            middlename = model.middlename.orEmpty(),
            phone = model.phone!!,
            phone_verified = model.phone_verified!!,
            username = model.username.orEmpty(),
            number = model.number.orEmpty()
        )
    }

    override fun domainToEntity(model: User): UserEntity {
        return UserEntity(
            id = model.id ,
            email = model.email,
            lastname = model.lastname,
            middlename = model.middlename,
            phone = model.phone,
            phone_verified = model.phone_verified,
            username = model.username,
            number = model.number,
        )
    }
}


