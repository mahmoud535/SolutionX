package com.example.solutionx.common.di

import android.content.Context
import com.example.solutionx.common.data.repository.local.LocalDsProvider
import com.example.solutionx.common.domain.repository.loca.ILocalDSProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object StorageDI {

    @Provides
    @Singleton
    fun provideLocalDSProvider(@ApplicationContext context: Context): ILocalDSProvider {
        return LocalDsProvider(context)
    }



    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context, gson: Gson): LocalDsProvider =
        LocalDsProvider(context)

    // Provide UserPreferences lazily
    @Provides
    @Singleton
    fun provideLazyUserPreferences(userPreferences: LocalDsProvider): Lazy<LocalDsProvider> =
        lazy { userPreferences }
}