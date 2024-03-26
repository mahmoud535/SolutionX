package com.example.solutionx.features.services.currency.data.repository.remote

import am.leon.solutionx.android.extentions.parseJsonFile
import android.content.Context
import com.example.solutionx.features.services.currency.data.model.dto.CurrencyDto
import com.example.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS

internal class CurrenciesRemoteDS(private val context: Context) :ICurrenciesRemoteDS{
    override fun getCurrencies(): List<CurrencyDto> {
        return parseJsonFile(context, "currencies.json")
    }
}