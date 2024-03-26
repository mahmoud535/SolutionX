package com.example.solutionx.features.services.currency.domain.repository

import com.example.solutionx.features.services.currency.domain.model.Currency

interface ICurrenciesRepository {
   fun getCurrenciesFromRemote(): List<Currency>
}