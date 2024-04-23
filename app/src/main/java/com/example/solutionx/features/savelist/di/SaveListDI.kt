package com.example.solutionx.features.savelist.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.features.savelist.data.repository.SaveListRepository
import com.example.solutionx.features.savelist.data.repository.local.LocalDataSource
import com.example.solutionx.features.savelist.domain.interactor.SaveListValuesUC
import com.example.solutionx.features.savelist.domain.repository.ISaveListRepository
import com.example.solutionx.features.savelist.domain.repository.local.ILocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SaveListDI {
    @Singleton
    @Provides
    fun provideSaveListRepository(
        localDataSource: ILocalDataSource
    ): ISaveListRepository {
        return SaveListRepository(localDataSource)
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(
        keyValueStorage: ILocalDSProvider
    ): ILocalDataSource {
        return LocalDataSource(keyValueStorage)
    }

    @Singleton
    @Provides
    fun provideSaveListValuesUC(saveListRepository: ISaveListRepository): SaveListValuesUC {
        return SaveListValuesUC(saveListRepository)
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context,
        factory: HiltWorkerFactory
    ): WorkManager {
        val configuration =
            Configuration
                .Builder()
                .setWorkerFactory(factory)
                .build()
        WorkManager
            .initialize(context, configuration)
        return WorkManager.getInstance(context)
    }
}