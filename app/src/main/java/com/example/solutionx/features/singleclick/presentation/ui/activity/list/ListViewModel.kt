package com.example.solutionx.features.singleclick.presentation.ui.activity.list

//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import com.example.solutionx.features.singleclick.domain.model.Countries
//import com.example.solutionx.features.singleclick.domain.model.Currencies
//import com.example.solutionx.features.singleclick.domain.model.Filter
//import com.example.solutionx.features.singleclick.domain.usecases.GetAllCountryUseCase
//import com.example.solutionx.features.singleclick.domain.usecases.GetAllCurrenciesUseCase
//import com.example.solutionx.features.singleclick.domain.usecases.GetFiltersUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.update
//import javax.inject.Inject
//
//@HiltViewModel
//class ListViewModel @Inject constructor(
//    private val getAllCountriesUseCase: GetAllCountryUseCase ,
//    private val getAllCurrenciesUseCase: GetAllCurrenciesUseCase ,
//    private val getAllFiltersUseCase: GetFiltersUseCase) :ViewModel() {
//
//    private val _state = MutableStateFlow(MainUiState())
//    val state = _state.asStateFlow()
//    private val _context = MutableStateFlow<Context?>(null)
//    val context =_context.asStateFlow()
//    init {
//        getAllCurrency(getAllCurrenciesUseCase.invoke(context.value!!.applicationContext))
//    }
//
//    private fun getAllCurrency(currencyList: List<Currencies>) {
//        _state.update {
//            it.copy(
//                currency = currencyList.toCurrencyUiState() ,
//                model = currencyList.toCurrencyUiState().toModelList()
//
//            )
//        }
//
//    }
//
//    private fun getAllCountry(countryList: List<Countries>) {
//        _state.update {
//            it.copy(
//                country = countryList.toCountryUiState(),
//                model = countryList.toCountryUiState().toModelList()
//            )
//        }
//    }
//
//    private fun getAllFilter(filterList: List<Filter>) {
//        _state.update {
//            it.copy(
//                filter = filterList.toFilterUiState(),
//                model = filterList.toFilterUiState().toModelList()
//            )
//        }
//
//    }
//
//
//    fun updateModel(list :List<Model>, selectedItemId:Int):List<Model>{
//        return list.map { item ->
//            item.copy(isSelected = item.id == selectedItemId)
//        }
//
//    }
//}