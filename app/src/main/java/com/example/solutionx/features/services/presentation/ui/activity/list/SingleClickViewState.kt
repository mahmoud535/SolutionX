package com.example.solutionx.features.services.presentation.ui.activity.list

import com.example.solutionx.features.services.country.domain.models.Country

sealed class SingleClickViewState {
    data class Success(val countries: List<Country>) : SingleClickViewState()
    object Loading : SingleClickViewState()
    data class Error(val message: String) : SingleClickViewState()
}