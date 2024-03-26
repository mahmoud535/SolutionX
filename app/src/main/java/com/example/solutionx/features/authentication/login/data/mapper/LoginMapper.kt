package com.example.solutionx.features.authentication.login.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.authentication.login.data.model.dto.LoginDto
import com.example.solutionx.features.authentication.login.data.model.dto.UserDto
import com.example.solutionx.features.authentication.login.data.model.entity.LoginEntity
import com.example.solutionx.features.authentication.login.domain.model.Login


internal object LoginMapper : Mapper<LoginDto, Login, LoginEntity>() {
    override fun dtoToDomain(model: LoginDto): Login {
        return Login(
            message = model.message.orEmpty(),
            token = model.token.orEmpty(),
            user = UserMapper.dtoToDomain(model.user ?: UserDto())
        )
    }

    override fun domainToEntity(model: Login): LoginEntity {
        return LoginEntity(
            message = model.message,
            token = model.token,
            user = UserMapper.domainToEntity(model.user)
        )
    }
}