package com.example.solutionx.features.savelist.domain.interactor

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.domain.repository.usecase.BaseUseCase
import com.example.solutionx.features.savelist.domain.repository.ISaveListRepository
import com.example.solutionx.features.services.country.domain.models.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveListValuesUC @Inject constructor(private val repository: ISaveListRepository) {

    operator fun invoke(
        scope: CoroutineScope,
        list: List<String>,
        onResult: (Resource<List<String>>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            BaseUseCase.execute(scope, call = {
                repository.saveList(list)
                repository.getList()
            }, onResult = onResult)
        }
    }

}