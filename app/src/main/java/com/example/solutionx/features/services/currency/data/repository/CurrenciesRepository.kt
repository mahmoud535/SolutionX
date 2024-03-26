package com.example.solutionx.features.services.currency.data.repository

import com.example.solutionx.features.services.currency.data.mapper.CurrencyMapper
import com.example.solutionx.features.services.currency.domain.model.Currency
import com.example.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import com.example.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS

internal class CurrenciesRepository(private val remoteDS: ICurrenciesRemoteDS): ICurrenciesRepository {
    override fun getCurrenciesFromRemote(): List<Currency> {
        val result =  remoteDS.getCurrencies()
        return CurrencyMapper.dtoToDomain(result)
    }
}