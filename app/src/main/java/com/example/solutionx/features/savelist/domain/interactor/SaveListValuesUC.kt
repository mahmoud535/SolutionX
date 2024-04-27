package com.example.solutionx.features.savelist.domain.interactor

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.domain.repository.usecase.BaseUseCase
import com.example.solutionx.features.savelist.domain.repository.ISaveListRepository
import com.example.solutionx.features.services.country.domain.models.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveListValuesUC @Inject constructor(private val repository: ISaveListRepository) {
    suspend fun saveList(list: List<String>){
        repository.saveList(list)
    }

     fun getList(): Flow<Resource<List<String>>> {
        return flow {
            val list = repository.getList()
            emit(Resource.loading())
            emit(Resource.success(list))
        }
    }


}