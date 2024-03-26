package com.example.solutionx.features.services.presentation.ui.activity.list

import com.example.solutionx.features.authentication.login.presentation.ui.activity.login.LoginViewState
import com.example.solutionx.features.services.country.domain.models.Country
import com.example.solutionx.features.services.currency.domain.model.Currency
import com.example.solutionx.features.services.filter.domain.model.Filter

sealed class SingleClickViewState {
    data class Success(val countries: List<Country>) : SingleClickViewState()
    object Loading : SingleClickViewState()
    data class Error(val message: String) : SingleClickViewState()
}