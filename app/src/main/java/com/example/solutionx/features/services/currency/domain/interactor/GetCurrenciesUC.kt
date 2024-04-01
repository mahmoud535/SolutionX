package com.example.solutionx.features.services.currency.domain.interactor

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.features.services.currency.domain.model.Currency
import com.example.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrenciesUC @Inject constructor(private val repository: ICurrenciesRepository){
    operator fun invoke(scope: CoroutineScope, onResult: (Resource<List<Currency>>) -> Unit){
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO) {
                    val countries = repository.getCurrenciesFromRemote()
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