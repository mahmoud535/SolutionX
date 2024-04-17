package com.example.solutionx.features.login.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.solutionx.common.data.repository.local.localds.LocalDsProvider
import com.example.solutionx.common.data.repository.remote.RestApiNetworkProvider
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.common.domain.repository.loca.encryption.IEncryptionProvider
import com.example.solutionx.features.login.data.repository.LoginRepository
import com.example.solutionx.features.login.data.repository.local.LoginLocalDS
import com.example.solutionx.features.login.data.repository.remote.LoginRemoteDS
import com.example.solutionx.features.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithSocialUC
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object LoginDI {
    @Provides
    fun provideRestApiNetworkProvider(serviceApi: ServiceApi): IRestApiNetworkProvider {
        return RestApiNetworkProvider(serviceApi)
    }

    @Provides
    fun provideRemoteDS(provider: IRestApiNetworkProvider): ILoginRemoteDS = LoginRemoteDS(provider)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideLocalDS(userPreferences: LocalDsProvider,dataEncryption:IEncryptionProvider): ILoginLocalDS = LoginLocalDS(userPreferences,dataEncryption)

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