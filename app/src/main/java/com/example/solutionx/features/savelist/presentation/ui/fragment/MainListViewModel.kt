package com.example.solutionx.features.savelist.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.presentation.viewModel.BaseViewModel
import com.example.solutionx.features.login.presentation.util.Constants
import com.example.solutionx.features.savelist.domain.interactor.ListUpdateWorker
import com.example.solutionx.features.savelist.domain.interactor.SaveListValuesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val saveListValuesUC: SaveListValuesUC,
    private val workManager: WorkManager
) :  BaseViewModel<MainListState, MainListIntent>() {
    override val initialState: MainListState = MainListState.Loading
//
//    private val _viewState = MutableStateFlow<MainListState>(MainListState.IdleState)
//    val viewState: StateFlow<MainListState> get() = _viewState

    override fun handleIntent(intent: MainListIntent) {
        when (intent) {
            is MainListIntent.SaveNamesIntent -> saveNames(intent)
            is MainListIntent.UpdateNamesListIntent -> updateNamesList(intent.names)
        }
    }

    private fun saveNames(intent: MainListIntent.SaveNamesIntent) {
        viewModelScope.launch {
            saveListValuesUC.saveList(intent.names)
            getNamesList()
        }
    }

    private fun getNamesList() {
        viewModelScope.launch {
            saveListValuesUC.getList().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val data = resource.model
                        _viewState.value = MainListState.Success(data)
                    }
                    is Resource.Failure -> {
                        val error = resource.exception
                        _viewState.value = MainListState.Error(error)
                    }
                    else -> {}
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
                            _viewState.value =
                                MainListState.Error(Exception("Failed to update names list"))
                        }

                        else -> {}
                    }
                }
            workManager.enqueueUniqueWork(
                Constants.WORK_NAME,
                ExistingWorkPolicy.KEEP,
                workRequest
            )

            workManager.getWorkInfoByIdFlow(workRequest.id).collect { workInfo ->
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> {
                            logger.debug(String::class.java, "Worker is ENQUEUED")
                        }

                        WorkInfo.State.RUNNING -> {
                            logger.debug(String::class.java, "Worker is RUNNING")
                        }

                        WorkInfo.State.SUCCEEDED -> {
                            workManager.cancelWorkById(workRequest.id)
                            logger.debug(String::class.java, "Worker is SUCCEEDED")
                            logger.debug(String::class.java, "List updated Successfully..")
                        }

                        WorkInfo.State.FAILED -> {
                            logger.debug(String::class.java, "Worker is FAILED")
                        }

                        WorkInfo.State.BLOCKED -> {
                            logger.debug(String::class.java, "Worker is BLOCKED")
                        }

                        WorkInfo.State.CANCELLED -> {
                            logger.debug(String::class.java, "Worker is CANCELLED")
                        }
                    }
                }else {
                    logger.error("workInfo is null")
                }
            }
            }
        }

        private fun createInputData(names: List<String>): Data {
            val inputDataBuilder = Data.Builder()
            inputDataBuilder.putStringArray(ListUpdateWorker.KEY_NAMES_LIST, names.toTypedArray())
            return inputDataBuilder.build()
        }

        companion object {
            private val logger = LoggerFactory.getLogger(MainListViewModel::class.java)
        }

    }







