package com.example.solutionx.features.login.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.domain.model.Login


internal object LoginMapper : Mapper<LoginDto, Login, LoginEntity>() {
    override fun dtoToDomain(model: LoginDto): Login {
        return Login(
            message = model.message.orEmpty(),
            accessToken = model.token.orEmpty(),
            userInfo = UserMapper.dtoToDomain(model.user!!)
        )
    }

    override fun domainToEntity(model: Login): LoginEntity {
        return LoginEntity(
            token = model.accessToken,
            user = UserMapper.domainToEntity(model.userInfo)
        )

    }


}