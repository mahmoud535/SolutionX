package com.example.solutionx.features.services.filter.domain.interactor

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.features.services.currency.domain.model.Currency
import com.example.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import com.example.solutionx.features.services.filter.domain.model.Filter
import com.example.solutionx.features.services.filter.domain.repository.IFilterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFilterUC @Inject constructor(private val repository: IFilterRepository) {

    operator fun invoke(scope: CoroutineScope, onResult: (Resource<List<Filter>>) -> Unit){
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO) {
                    val countries = repository.getFilterFromRemote()
                    onResult.invoke(Resource.success(countries))
                }
                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.IO) {
                    val failureResource = if (e is LeonException)
                        e
                    else
                        LeonException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")

                    onResult.invoke(Resource.failure(failureResource))
                }

                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }
}