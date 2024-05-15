package com.example.solutionx.features.signup.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.solutionx.common.data.repository.local.localds.LocalDsProvider
import com.example.solutionx.common.data.repository.remote.RestApiNetworkProvider
import com.example.solutionx.common.data.repository.remote.ServiceApi
import com.example.solutionx.common.domain.repository.loca.encryption.IEncryptionProvider
import com.example.solutionx.common.domain.repository.remote.IRestApiNetworkProvider
import com.example.solutionx.features.login.domain.interactor.login.LoginWithEmailUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithPhoneUC
import com.example.solutionx.features.login.domain.interactor.login.LoginWithSocialUC
import com.example.solutionx.features.signup.data.repository.SignupRepository
import com.example.solutionx.features.signup.data.repository.local.SignupLocalDS
import com.example.solutionx.features.signup.data.repository.remote.SignupRemoteDS
import com.example.solutionx.features.signup.domain.repository.ISignupRepository
import com.example.solutionx.features.signup.domain.repository.local.ISignupLocalDS
import com.example.solutionx.features.signup.domain.repository.remote.ISignupRemoteDS
import com.example.solutionx.features.signup.domain.usecase.SignupUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object SignUpDI {

//    @Provides
//    fun provideRestApiNetworkProvider(serviceApi: ServiceApi): IRestApiNetworkProvider {
//        return RestApiNetworkProvider(serviceApi)
//    }

    @Provides
    fun provideRemoteDS(provider: IRestApiNetworkProvider): ISignupRemoteDS = SignupRemoteDS(provider)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideLocalDS(userPreferences: LocalDsProvider, dataEncryption: IEncryptionProvider): ISignupLocalDS = SignupLocalDS(userPreferences,dataEncryption)

    @Provides
    fun provideRepository(remoteDS: ISignupRemoteDS, localDS: ISignupLocalDS): ISignupRepository =
        SignupRepository(remoteDS, localDS)

//    @Provides
//    fun provideLoginWithEmailUC(repository: ISignupRepository): LoginWithEmailUC =
//        LoginWithEmailUC(repository)

    @Provides
    fun provideLoginWithPhoneUC(repository: ISignupRepository): SignupUC =
        SignupUC(repository)

//    @Provides
//    fun provideLoginWithSocialUC(repository: ISignupRepository): LoginWithSocialUC =
//        LoginWithSocialUC(repository)
}