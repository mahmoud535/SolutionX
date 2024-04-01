package com.example.solutionx.features.services.country.data.repository

import com.example.solutionx.features.services.country.data.mapper.CountryMapper
import com.example.solutionx.features.services.country.domain.models.Country
import com.example.solutionx.features.services.country.domain.repository.ICountriesRepository
import com.example.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS

internal class CountriesRepository(private val remoteDS: ICountriesRemoteDS): ICountriesRepository {
    override fun getCountriesFromRemote(): List<Country> {
        val result = remoteDS.getCountries()
        return CountryMapper.dtoToDomain(result)
    }
}