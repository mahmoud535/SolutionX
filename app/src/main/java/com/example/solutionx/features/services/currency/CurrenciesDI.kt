package com.example.solutionx.features.services.currency

import android.content.Context
import com.example.solutionx.features.services.currency.data.repository.CurrenciesRepository
import com.example.solutionx.features.services.currency.data.repository.remote.CurrenciesRemoteDS
import com.example.solutionx.features.services.currency.domain.interactor.GetCurrenciesUC
import com.example.solutionx.features.services.currency.domain.repository.ICurrenciesRepository
import com.example.solutionx.features.services.currency.domain.repository.remote.ICurrenciesRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
internal object CurrenciesDI {

    @Provides
    fun provideRemoteDS(@ApplicationContext context:Context):ICurrenciesRemoteDS =
        CurrenciesRemoteDS(context)

    @Provides
    fun provideRepository(remoteDS:ICurrenciesRemoteDS):ICurrenciesRepository =
        CurrenciesRepository(remoteDS)

    @Provides
    fun provideGetCurrenciesUC(repository: ICurrenciesRepository): GetCurrenciesUC {
        return GetCurrenciesUC(repository)
    }
}