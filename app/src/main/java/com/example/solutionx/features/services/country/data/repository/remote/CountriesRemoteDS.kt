package com.example.solutionx.features.services.country.data.repository.remote

import am.leon.solutionx.android.extentions.parseJsonFile
import android.content.Context
import com.example.solutionx.features.services.country.data.model.dto.CountryDto
import com.example.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS

internal class CountriesRemoteDS(private val context: Context) : ICountriesRemoteDS {
    override fun getCountries(): List<CountryDto> {
        return parseJsonFile(context, "countries.json")
    }
}