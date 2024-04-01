package com.example.solutionx.features.services.currency.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.services.currency.data.model.dto.CurrencyDto
import com.example.solutionx.features.services.currency.data.model.entity.CurrencyEntity
import com.example.solutionx.features.services.currency.domain.model.Currency

internal object CurrencyMapper : Mapper<CurrencyDto, Currency, CurrencyEntity>() {
    override fun dtoToDomain(model: CurrencyDto): Currency {
        return Currency(
            id = model.id ?: -1,
            name = model.name.orEmpty(),
            sign = model.sign.orEmpty(),
            code = model.code.orEmpty(),
            selected = false
        )
    }

    override fun domainToEntity(model: Currency): CurrencyEntity {
        return CurrencyEntity(
            id = model.id,
            name = model.name,
            sign = model.sign,
            code = model.code
        )
    }
}