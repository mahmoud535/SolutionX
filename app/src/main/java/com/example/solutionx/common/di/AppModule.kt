package com.example.solutionx.common.di

import android.content.Context
import com.example.solutionx.features.authentication.login.data.local.UserPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {
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