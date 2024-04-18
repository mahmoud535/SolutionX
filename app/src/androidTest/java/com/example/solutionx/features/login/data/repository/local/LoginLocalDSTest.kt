package com.example.solutionx.features.login.data.repository.local

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SdkSuppress
import com.example.solutionx.common.data.repository.local.encryption.EncryptionProvider
import com.example.solutionx.common.data.repository.local.localds.StorageKeyEnum
import com.example.solutionx.common.di.StorageDI.provideLocalDSProvider
import com.example.solutionx.common.domain.repository.loca.encryption.IEncryptionProvider
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.base.CharMatcher.any
import com.google.gson.Gson
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.verify


@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class LoginLocalDSTest{
    private lateinit var keyValueStorage: ILocalDSProvider
    private lateinit var gson: Gson
    private lateinit var localDataSource: LoginLocalDS
    private lateinit var encryptionProvider: IEncryptionProvider
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        keyValueStorage = provideLocalDSProvider(context)
        encryptionProvider = mockk()
        gson = Gson()
        localDataSource = LoginLocalDS(keyValueStorage,encryptionProvider)
    }

    @Test
    fun saveLoginSavesEncryptedLoginData() = runTest {
        // Given
        val user = UserEntity()
        val userJson = gson.toJson(user)
        val encryptedUser = "encryptedData"

        // Mock encryptionProvider behavior
        coEvery { encryptionProvider.encryptData(userJson.toByteArray()) } returns encryptedUser.toByteArray()

        // Mock keyValueStorage behavior
        coEvery { runBlocking{ keyValueStorage.save(StorageKeyEnum.USER, encryptedUser)} } just Runs

        // When
        localDataSource.saveUser(user)

        // Then
        coVerify {
            keyValueStorage.save(StorageKeyEnum.USER, encryptedUser)
        }

    }



}