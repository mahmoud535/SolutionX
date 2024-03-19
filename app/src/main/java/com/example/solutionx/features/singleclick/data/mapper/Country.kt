package com.example.solutionx.features.singleclick.data.mapper

import com.example.solutionx.features.singleclick.data.model.EntityCountries
import com.example.solutionx.features.singleclick.domain.model.Countries

fun EntityCountries.toCountry() = Countries(
    id = id,
    name = name,
    currency = currency,
    code = code,
    phone_code = phone_code,
    flag = flag,
    selected= selected

    )

