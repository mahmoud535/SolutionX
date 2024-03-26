package com.example.solutionx.features.services.country

import android.content.Context
import com.example.solutionx.features.services.country.data.repository.CountriesRepository
import com.example.solutionx.features.services.country.data.repository.remote.CountriesRemoteDS
import com.example.solutionx.features.services.country.domain.interactor.GetCountriesUC
import com.example.solutionx.features.services.country.domain.repository.ICountriesRepository
import com.example.solutionx.features.services.country.domain.repository.remote.ICountriesRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
internal object CountriesDI {

    @Provides
    fun provideRemoteDS(@ApplicationContext context: Context): ICountriesRemoteDS =
        CountriesRemoteDS(context)

    @Provides
    fun provideRepository(remoteDS: ICountriesRemoteDS): ICountriesRepository =
        CountriesRepository(remoteDS)

    @Provides
    fun provideGetCountriesUC(repository: ICountriesRepository): GetCountriesUC {
        return GetCountriesUC(repository)
    }
}