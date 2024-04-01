package com.example.solutionx.features.services.country.domain.repository

import com.example.solutionx.features.services.country.domain.models.Country

interface ICountriesRepository {
    fun getCountriesFromRemote(): List<Country>
}