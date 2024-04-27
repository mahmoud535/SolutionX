package com.example.solutionx.features.login.data.repository.local

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SdkSuppress
import com.example.solutionx.common.data.repository.local.localds.StorageKeyEnum
import com.example.solutionx.common.domain.repository.loca.encryption.IEncryptionProvider
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.features.login.data.model.entity.LoginEntity
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.google.gson.Gson
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Base64

/* test cases
1. save login with valid response then save encrypted login data
2. save login with empty response then do nothing
3. get access token then return decrypted access token
4. get access token with no access token is stored then return exception
5.get access token when IV is empty then return exception
6. get access token when stored data cannot be decrypted then return empty string
7. get user then return decrypted user
8. get user with no user is stored then return empty user
9. get user when stored data cannot be decrypted then return empty string
* */

@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class LoginLocalDSTest{
//    private lateinit var iLocalDSProvider: ILocalDSProvider
//    private lateinit var gson: Gson
//    private lateinit var localDataSource: LoginLocalDS
//    private lateinit var encryptionProvider: IEncryptionProvider
//    @Before
//    fun setUp() {
//        iLocalDSProvider = mockk()
//        encryptionProvider = mockk()
//        gson = Gson()
//        localDataSource = LoginLocalDS(iLocalDSProvider,encryptionProvider)
//    }
//
//    @Test
//    fun saveLoginSavesEncryptedLoginData() = runTest {
//        val user = UserEntity()
//        val userJson = gson.toJson(user)
//        val encryptedUser = "encryptedData"
//
//        // Mock encryptionProvider behavior
//        coEvery { encryptionProvider.encryptData(userJson.toByteArray()) } returns encryptedUser.toByteArray()
//
//        // Mock keyValueStorage behavior
//        coEvery { runBlocking{ iLocalDSProvider.save(StorageKeyEnum.USER, encryptedUser)} } just Runs
//
//        // When
//        localDataSource.saveUser(user)
//
//        // Then
//        coVerify {
//            iLocalDSProvider.save(StorageKeyEnum.USER, encryptedUser)
//        }
//
//    }
//
//
//
//
//    @Test
//    fun `saveLogin() with valid data then save this data encrypted`() = runTest {
//        val loginResponse =
//            LoginEntity("testToken", UserEntity(1, "testEmail", "testPhone"))
//
//        whenever(encryptionProvider.encryptData(any())).thenReturn(Pair(ByteArray(0), ByteArray(0)))
//
//        localDataSource.saveLogin(loginResponse)
//
//        verify(keyValueStorage).save(LocalDataSource.USER_ENCRYPTED, "")
//        verify(keyValueStorage).save(LocalDataSource.USER_IV, "")
//        verify(keyValueStorage).save(LocalDataSource.ACCESS_TOKEN_ENCRYPT, "")
//        verify(keyValueStorage).save(LocalDataSource.ACCESS_TOKEN_IV, "")
//        verify(keyValueStorage).save(LocalDataSource.IS_USER_LOGGED_IN, "true")
//    }
//
//    @Test
//    fun `saveLogin() with empty data then do nothing`() = runTest {
//        val loginResponse = LoginResponseEntity("", UserEntity(0, "", ""))
//
//        localDataSource.saveLogin(loginResponse)
//
//        verify(keyValueStorage).save(LocalDataSource.IS_USER_LOGGED_IN, "false")
//    }
//
//    @Test
//    fun `getAccessToken() then return decrypted access token`() = runTest {
//        val validBase64String = Base64.getEncoder().encodeToString("testToken".toByteArray())
//
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_ENCRYPT)).thenReturn(
//            validBase64String
//        )
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_IV)).thenReturn(
//            validBase64String
//        )
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("testToken")
//
//        val result = localDataSource.getAccessToken()
//
//        assert(result == "testToken")
//
//
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun `getAccessToken() with no access token is stored then return exception`() = runTest {
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_ENCRYPT)).thenReturn(
//            ""
//        )
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("")
//
//        val result = localDataSource.getAccessToken()
//
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun `getAccessToken() with empty IV then return exception`() = runTest {
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_ENCRYPT)).thenReturn(
//            "testToken"
//        )
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_IV)).thenReturn("")
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("testToken")
//
//        val result = localDataSource.getAccessToken()
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun `getAccessToken() when stored data cannot be decrypted then return exception`() = runTest {
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_ENCRYPT)).thenReturn(
//            "testToken"
//        )
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.ACCESS_TOKEN_IV)).thenReturn("testIv")
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("")
//
//        val result = localDataSource.getAccessToken()
//
//    }
//
//    @Test
//    fun `getUser() then return decrypted user`() = runTest {
//        val userEntity = UserEntity(1, "testEmail", "testPhone")
//        val userJson = gson.toJson(userEntity)
//        val validBase64String = Base64.getEncoder().encodeToString(userJson.toByteArray())
//
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_ENCRYPTED)).thenReturn(validBase64String)
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_IV)).thenReturn(validBase64String)
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn(userJson)
//
//        val result = localDataSource.getUser()
//
//        TestCase.assertEquals(userEntity, result)
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun `getUser() with no user is stored then return exception`() = runTest {
//        val validBase64String = Base64.getEncoder().encodeToString("".toByteArray())
//
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_ENCRYPTED)).thenReturn(validBase64String)
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_IV)).thenReturn(validBase64String)
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("")
//
//        localDataSource.getUser()
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun `getUser() when stored data cannot be decrypted then return exception`() = runTest {
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_ENCRYPTED)).thenReturn("testUser")
//        whenever(keyValueStorage.get<String, String>(LocalDataSource.USER_IV)).thenReturn("testIv")
//        whenever(keystoreUtils.decrypt(any(), any())).thenReturn("")
//
//        val result = localDataSource.getUser()
//    }
//
//



}