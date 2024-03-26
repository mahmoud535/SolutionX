package com.example.solutionx.features.services.filter

import android.content.Context
import com.example.solutionx.features.services.currency.data.repository.CurrenciesRepository
import com.example.solutionx.features.services.currency.data.repository.remote.CurrenciesRemoteDS
import com.example.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import com.example.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS
import com.example.solutionx.features.services.filter.data.repository.FilterRepository
import com.example.solutionx.features.services.filter.data.repository.remote.FilterRemoteDS
import com.example.solutionx.features.services.filter.domain.interactor.GetFilterUC
import com.example.solutionx.features.services.filter.domain.repository.IFilterRepository
import com.example.solutionx.features.services.filter.domain.repository.remote.IFilterRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal object FilterDI {

    @Provides
    fun provideRemoteDS(@ApplicationContext context: Context): IFilterRemoteDS =
        FilterRemoteDS(context)

    @Provides
    fun provideRepository(remoteDS: IFilterRemoteDS): IFilterRepository =
        FilterRepository(remoteDS)

    @Provides
    fun provideGetFilterUC(repository: IFilterRepository): GetFilterUC {
        return GetFilterUC(repository)
    }
}