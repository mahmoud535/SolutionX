package com.example.solutionx.features.singleclick.data.repositoryimp

import android.content.Context
import com.example.solutionx.features.singleclick.domain.model.Countries
import com.example.solutionx.features.singleclick.domain.model.Currencies
import com.example.solutionx.features.singleclick.domain.model.Filter
import com.example.solutionx.features.singleclick.domain.repository.Repository
import javax.inject.Inject

//class RepositoryImp @Inject constructor(private val localDs:LocalDSImp): Repository {
//    override fun getAllCountries(context: Context): List<Countries> =
//          localDs.getAllCountries(context).map { it.toCountry() }
//
//
//    override fun getAllCurrencies(context: Context): List<Currencies> =
//        localDs.getAllCurrencies(context).map { it.toCurrency() }
//
//    override fun getAllFilters(context: Context): List<Filter> =
//        localDs.getAllFilters(context).map { it.toFilter() }
//}