package com.example.solutionx.features.services.filter.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class FilterDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
)