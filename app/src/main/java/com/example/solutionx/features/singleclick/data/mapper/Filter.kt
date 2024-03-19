package com.example.solutionx.features.singleclick.data.mapper

import com.example.solutionx.features.singleclick.domain.model.EntityFilter
import com.example.solutionx.features.singleclick.domain.model.Filter

fun EntityFilter.toFilter() = Filter(
    id = id,
    name = name,
    selected = selected
    )