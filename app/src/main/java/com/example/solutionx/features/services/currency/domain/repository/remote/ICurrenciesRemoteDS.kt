package com.example.solutionx.features.services.currency.domain.repository.remote

import com.example.solutionx.features.services.currency.data.model.dto.CurrencyDto

internal interface ICurrenciesRemoteDS {
    fun getCurrencies(): List<CurrencyDto>
}