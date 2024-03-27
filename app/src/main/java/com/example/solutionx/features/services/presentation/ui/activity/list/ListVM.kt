package com.example.solutionx.features.services.presentation.ui.activity.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.services.country.domain.interactor.GetCountriesUC
import com.example.solutionx.features.services.currency.domain.interactor.GetCurrenciesUC
import com.example.solutionx.features.services.filter.domain.interactor.GetFilterUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val countriesUC: GetCountriesUC,
    private val currenciesUC: GetCurrenciesUC,
    private val filterUC: GetFilterUC
) : ViewModel() {
    private val _viewState = MutableStateFlow<SingleClickViewState>(SingleClickViewState.Loading)
    val viewState: StateFlow<SingleClickViewState> = _viewState


    fun processIntent(intent:SingleClickIntent){
        when(intent){
            is SingleClickIntent.FetchData -> fetchData()
        }
    }

    private fun fetchData() {
         viewModelScope.launch {
             countriesUC.invoke(viewModelScope){ resource ->
                 when(resource){
                     is Resource.Progress -> _viewState.update { SingleClickViewState.Loading }
                     is Resource.Success -> _viewState.update { SingleClickViewState.Success(resource.model) }
                     is Resource.Failure -> _viewState.update { SingleClickViewState.Error(resource.exception.message ?: "error") }
                 }
             }
         }
    }
}