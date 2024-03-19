package com.example.solutionx.features.singleclick.data.mapper

import com.example.solutionx.features.singleclick.domain.model.Currencies
import com.example.solutionx.features.singleclick.domain.model.EntityCurrencies

fun EntityCurrencies.toCurrency() = Currencies(
    id = id,
    name = name,
    code = code,
    sign = sign,
    selected = selected
    )