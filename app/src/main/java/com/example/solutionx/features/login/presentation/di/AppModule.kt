package com.example.solutionx.features.login.presentation.di

import android.content.Context
import com.example.solutionx.features.login.data.local.UserDao
import com.example.solutionx.features.login.data.local.UserDatabase
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.remote.ServiceApi
import com.example.solutionx.features.login.data.repositoryimp.AuthRepositoryImpl
import com.example.solutionx.features.login.domain.repository.remote.RemoteRepo
import com.example.solutionx.features.singleclick.data.repositoryimp.LanguageRepositoryImp
import com.example.solutionx.features.singleclick.domain.repository.LanguageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BaseUrl = "https://my-json-server.typicode.com/"
    @Singleton
    @Provides
    fun getRetroBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }


    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideLanguageRepository(@ApplicationContext context: Context): LanguageRepository {
        return LanguageRepositoryImp(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }


    @Singleton
    @Provides
    fun provideAuthRepository(
        remoteDataSource: ServiceApi,
        localDataSource: UserDatabase,
        userMapper: UserMapper
    ): RemoteRepo {
        return AuthRepositoryImpl(remoteDataSource, localDataSource, userMapper)
    }



    @Singleton
    @Provides
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

}