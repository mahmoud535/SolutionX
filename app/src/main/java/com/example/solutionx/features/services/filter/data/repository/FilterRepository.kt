package com.example.solutionx.features.services.filter.data.repository

import com.example.solutionx.features.services.filter.data.mapper.FilterMapper
import com.example.solutionx.features.services.filter.domain.model.Filter
import com.example.solutionx.features.services.filter.domain.repository.IFilterRepository
import com.example.solutionx.features.services.filter.domain.repository.remote.IFilterRemoteDS

internal class FilterRepository(private val repository:IFilterRemoteDS):IFilterRepository {
    override fun getFilterFromRemote(): List<Filter> {
        val result = repository.getFilter()
        return FilterMapper.dtoToDomain(result)
    }
}