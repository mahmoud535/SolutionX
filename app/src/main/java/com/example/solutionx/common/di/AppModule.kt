package com.example.solutionx.common.di

import android.content.Context
import com.example.solutionx.BuildConfig
import com.example.solutionx.common.data.repository.local.LocalDsProvider
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.common.domain.repository.loca.ILocalDSProvider
import com.example.solutionx.features.login.presentation.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}