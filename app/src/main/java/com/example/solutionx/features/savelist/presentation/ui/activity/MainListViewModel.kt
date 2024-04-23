package com.example.solutionx.features.savelist.presentation.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.features.login.presentation.ui.activity.login.LoginState
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker.Companion.KEY_NAMES_LIST
import com.example.solutionx.features.savelist.domain.interactor.SaveListValuesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    private val workManager: WorkManager
) :  ViewModel() {
    private val _state = MutableStateFlow<MainListState>(MainListState.IdleState)
    val state: StateFlow<MainListState> get() = _state


    fun handleIntent(intent: MainListIntent) {
        when (intent) {
            is MainListIntent.SaveNamesIntent -> saveNames(intent.names)
            is MainListIntent.UpdateNamesListIntent -> updateNamesList(intent.names)
        }
    }

    private fun saveNames(names: List<String>) {
        viewModelScope.launch {
            _state.value = MainListState.Loading
            saveListValuesUC(viewModelScope, names) { resource ->
                 when (resource) {
                    is Resource.Success -> {
                        _state.update{ MainListState.Success(resource.model) }
                    }
                    is Resource.Failure -> _state.update { MainListState.Error(resource.exception) }
                    is Resource.Progress ->  _state.update { MainListState.Loading }
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
                            _state.value = MainListState.Success(names)
                        }
                        WorkInfo.State.FAILED -> {
                            _state.value = MainListState.Error(Exception("Failed to update names list"))
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







