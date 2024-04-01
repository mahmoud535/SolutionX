package com.example.solutionx.common.di

import android.content.Context
import com.example.solutionx.BuildConfig
import com.example.solutionx.common.data.repository.local.LocalDsProvider
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.common.domain.repository.loca.ILocalDSProvider
import com.example.solutionx.features.login.data.local.UserPreferences
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
    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG){
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)

    @Provides
    @Singleton
    fun provideLocalDSProvider(@ApplicationContext context: Context): ILocalDSProvider {
        return LocalDsProvider(context)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context,gson: Gson): UserPreferences =
        UserPreferences(context,gson)

    // Provide UserPreferences lazily
    @Provides
    @Singleton
    fun provideLazyUserPreferences(userPreferences: UserPreferences): Lazy<UserPreferences> =
        lazy { userPreferences }
}