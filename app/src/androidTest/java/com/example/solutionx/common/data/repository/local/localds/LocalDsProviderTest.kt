package com.example.solutionx.common.data.repository.local.localds

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.common.domain.repository.loca.localds.IStorageKeyEnum
import com.example.solutionx.features.login.data.model.entity.UserEntity
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionx.features.login.presentation.util.Constants
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDsProviderTest{
    private lateinit var localDsProvider: LocalDsProvider
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        localDsProvider = LocalDsProvider(context)
    }

    @Test
    fun testSaveAndGetAccessToken() {
        runBlocking {
            val key = StorageKeyEnum.ACCESS_TOKEN
            val value = "testAccessToken"

            localDsProvider.save(key, value)
            val retrievedValue: String = localDsProvider.get(key,"")

            assertEquals(value, retrievedValue)
        }
    }

    @Test
    fun testSaveAndGetUser() {
        runBlocking {
            val key = StorageKeyEnum.USER
            val value = "testValue"

            localDsProvider.save(key, value)
            val retrievedValue: String =  localDsProvider.get(key, "")
            Assert.assertEquals(value, retrievedValue)
        }
    }

    @Test(expected = LeonException.Local.IOOperation::class)
    fun testSaveWithUnsupportedType() {
        runBlocking {
            val key = StorageKeyEnum.USER
            val unsupportedValue = 1.0  // Unsupported type
            localDsProvider.save(key, unsupportedValue)
        }
    }

    @Test(expected = LeonException.Local.IOOperation::class)
    fun testGetWithUnsupportedType() {
        runBlocking {
            val key = StorageKeyEnum.USER
            val defaultValue = 1.0  // Unsupported default type
            localDsProvider.get(key, defaultValue)
        }
    }


}