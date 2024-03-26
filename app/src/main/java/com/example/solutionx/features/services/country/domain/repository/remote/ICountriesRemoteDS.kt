package com.example.solutionx.features.services.country.domain.repository.remote

import com.example.solutionx.features.services.country.data.model.dto.CountryDto

internal interface ICountriesRemoteDS {
    fun getCountries(): List<CountryDto>
}