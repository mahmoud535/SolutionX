package com.example.solutionx.features.services.filter.domain.repository.remote

import com.example.solutionx.features.services.filter.data.model.dto.FilterDto

internal interface IFilterRemoteDS {
    fun getFilter(): List<FilterDto>
}