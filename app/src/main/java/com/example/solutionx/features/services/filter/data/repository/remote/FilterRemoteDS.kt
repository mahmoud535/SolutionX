package com.example.solutionx.features.services.filter.data.repository.remote

import am.leon.solutionx.android.extentions.parseJsonFile
import android.content.Context
import com.example.solutionx.features.services.filter.data.model.dto.FilterDto
import com.example.solutionx.features.services.filter.domain.repository.remote.IFilterRemoteDS

internal class FilterRemoteDS(private val context:Context):IFilterRemoteDS {
    override fun getFilter(): List<FilterDto> {
        return parseJsonFile(context,"filter.json")
    }
}