package com.example.solutionx.features.savelist.presentation.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker.Companion.KEY_NAMES_LIST
import com.example.solutionx.features.savelist.domain.interactor.SaveListValuesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    private val workManager: WorkManager
) :  ViewModel() {
    private val _viewState = MutableStateFlow<MainListState>(MainListState.IdleState)
    val viewState: StateFlow<MainListState> get() = _viewState


    fun handleIntent(intent: MainListIntent) {
        when (intent) {
            is MainListIntent.SaveNamesIntent -> saveNames(intent.names)
            is MainListIntent.UpdateNamesListIntent -> updateNamesList(intent.names)
        }
    }

    private fun saveNames(names: List<String>) {
        viewModelScope.launch {
            _viewState.value = MainListState.Loading
            saveListValuesUC(viewModelScope, names) { resource ->
                 when (resource) {
                    is Resource.Success -> {
                        _viewState.update{ MainListState.Success(resource.model) }
                    }
                    is Resource.Failure -> _viewState.update { MainListState.Error(resource.exception) }
                    is Resource.Progress ->  _viewState.update { MainListState.Loading }
                }
            }
        }
    }

    private fun updateNamesList(names: List<String>) {
        viewModelScope.launch {
            val workRequest = OneTimeWorkRequestBuilder<ListUpdateWorker>()
                .setInputData(createInputData(names))
                .build()

            workManager.getWorkInfoByIdLiveData(workRequest.id)
                .observeForever { workInfo ->
                    when (workInfo?.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            _viewState.value = MainListState.Success(names)
                        }
                        WorkInfo.State.FAILED -> {
                            _viewState.value = MainListState.Error(Exception("Failed to update names list"))
                        }
                        else -> {  }
                    }
                }
            workManager.enqueueUniqueWork(
                "updateListValuesWorker",
                ExistingWorkPolicy.KEEP,
                workRequest)
        }
    }

    private fun createInputData(names: List<String>): Data {
        val inputDataBuilder = Data.Builder()
        inputDataBuilder.putStringArray(KEY_NAMES_LIST, names.toTypedArray())
        return inputDataBuilder.build()
    }

}







