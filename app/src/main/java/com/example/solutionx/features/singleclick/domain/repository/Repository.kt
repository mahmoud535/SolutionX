package com.example.solutionx.features.singleclick.domain.repository

import android.content.Context
import com.example.solutionx.features.singleclick.domain.model.Countries
import com.example.solutionx.features.singleclick.domain.model.Currencies
import com.example.solutionx.features.singleclick.domain.model.Filter

interface Repository {
    fun getAllCountries(context: Context): List<Countries>
    fun getAllCurrencies(context: Context): List<Currencies>
    fun getAllFilters(context: Context): List<Filter>
}