package com.example.solutionx.features.services.filter.data.mapper

import com.example.solutionx.common.data.mapper.Mapper
import com.example.solutionx.features.services.filter.data.model.dto.FilterDto
import com.example.solutionx.features.services.filter.data.model.entity.FilterEntity
import com.example.solutionx.features.services.filter.domain.model.Filter

internal object FilterMapper :Mapper<FilterDto, Filter,FilterEntity>() {
    override fun dtoToDomain(model: FilterDto): Filter {
       return Filter(
           id = model.id ?: -1,
           name = model.name.orEmpty(),
           selected = false
       )
    }

    override fun domainToEntity(model: Filter): FilterEntity {
        return FilterEntity(
            id = model.id ,
            name = model.name,
        )
    }
}