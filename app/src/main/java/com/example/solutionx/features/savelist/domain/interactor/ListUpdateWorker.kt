package com.example.solutionx.features.savelist.domain.interactor

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.solutionx.android.helpers.logging.LoggerFactory
import com.example.solutionx.common.data.model.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltWorker
class ListUpdateWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted  workerParams: WorkerParameters,
    private val saveListValuesUC: SaveListValuesUC
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

            return try {
                val names = inputData.getStringArray(KEY_NAMES_LIST)!!.toList()
                saveListValuesUC(CoroutineScope(Dispatchers.IO) ,names ) {resource ->
                        when (resource) {
                            is Resource.Success -> {
                              logger.info("Success")
                            }
                            is Resource.Failure -> {
                                logger.error("error in ListUpdateWorker")
                            }
                            is Resource.Progress -> { }
                        }
                    }
                Result.success(
                    Data.Builder().putString("Success", "List updated Successfully")
                        .build()
                )
            } catch (e: Exception) {
                logger.error("error in ListUpdateWorker")
                Result.failure(Data.Builder().putString("Error", e.message.toString()).build())
            }
    }


    companion object {
        const val KEY_NAMES_LIST = "key_NamesList"
        private val logger = LoggerFactory.getLogger(ListUpdateWorker::class.java)
    }
}