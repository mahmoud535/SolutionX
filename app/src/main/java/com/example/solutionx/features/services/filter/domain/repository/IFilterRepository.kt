package com.example.solutionx.features.services.filter.domain.repository

import com.example.solutionx.features.services.filter.domain.model.Filter

interface IFilterRepository {
    fun getFilterFromRemote():List<Filter>
}