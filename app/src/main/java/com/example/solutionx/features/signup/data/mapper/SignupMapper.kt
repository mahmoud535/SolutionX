package com.example.solutionx.features.signup.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.signup.data.model.dto.SignupDto
import com.example.solutionx.features.signup.data.model.entity.SignupEntity
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.model.signup.Signup


internal object SignupMapper : Mapper<SignupDto, Signup, SignupEntity>() {
    override fun dtoToDomain(model: SignupDto): Signup {
        return Signup(
            message = model.message.orEmpty(),
            token = model.token.orEmpty(),
            user = UserMapper.dtoToDomain(model.user!!)
        )
    }

    override fun domainToEntity(model: Signup): SignupEntity {
        return SignupEntity(
            token = model.token,
            user = UserMapper.domainToEntity(model.user)
        )

    }


}