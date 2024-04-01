package com.example.solutionx.features.login.di

import com.example.solutionx.BuildConfig
import com.example.solutionx.common.data.repository.remote.RestApiNetworkProvider
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.data.local.UserPreferences
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.features.login.data.repository.LoginRepository
import com.example.solutionx.features.login.data.repository.local.LoginLocalDS
import com.example.solutionx.features.login.data.repository.remote.LoginRemoteDS
import com.example.solutionx.features.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithSocialUC
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import com.example.solutionx.features.login.presentation.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
internal object LoginDI {
    @Provides
    fun provideRestApiNetworkProvider(serviceApi: ServiceApi): IRestApiNetworkProvider {
        return RestApiNetworkProvider(serviceApi)
    }

    @Provides
    fun provideRemoteDS(api: ServiceApi): ILoginRemoteDS = LoginRemoteDS(api)

    @Provides
    fun provideLocalDS(userPreferences: UserPreferences): ILoginLocalDS = LoginLocalDS(userPreferences)

    @Provides
    fun provideRepository(remoteDS: ILoginRemoteDS, localDS: ILoginLocalDS): ILoginRepository =
        LoginRepository(remoteDS, localDS)

    @Provides
    fun provideLoginWithEmailUC(repository: ILoginRepository): LoginWithEmailUC =
        LoginWithEmailUC(repository)

    @Provides
    fun provideLoginWithPhoneUC(repository: ILoginRepository): LoginWithPhoneUC =
        LoginWithPhoneUC(repository)

    @Provides
    fun provideLoginWithSocialUC(repository: ILoginRepository): LoginWithSocialUC =
        LoginWithSocialUC(repository)
}