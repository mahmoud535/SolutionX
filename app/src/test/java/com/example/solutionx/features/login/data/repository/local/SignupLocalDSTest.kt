package com.example.solutionx.features.login.data.repository.local

import com.example.solutionx.common.data.repository.local.localds.StorageKeyEnum
import com.example.solutionx.common.domain.repository.loca.localds.ILocalDSProvider
import com.example.solutionx.common.domain.repository.loca.localds.IStorageKeyEnum
import com.example.solutionx.features.savelist.data.repository.local.LocalDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever

import org.junit.Assert.assertEquals
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.argumentCaptor


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

class SignupLocalDSTest{

    private lateinit var localDSProvider: ILocalDSProvider
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setUp() {
        localDSProvider = mock(ILocalDSProvider::class.java)
        localDataSource = LocalDataSource(localDSProvider)
    }


    @Test
    fun saveList_Success() = runBlocking {
        val list = listOf("name1", "name2", "name3")

        // Capture the arguments passed to save method
        val keyCaptor = argumentCaptor<IStorageKeyEnum>()
        val valueCaptor = argumentCaptor<String>()

        localDataSource.saveList(list)

        // Verify that save method is called with the expected arguments
        verify(localDSProvider).save(keyCaptor.capture(), valueCaptor.capture())

        // Assert the captured arguments
        assertEquals(StorageKeyEnum.NAMES, keyCaptor.firstValue)
        assertEquals("name1,name2,name3", valueCaptor.firstValue.replace("\\s".toRegex(), ""))

    }

    @Test
    fun getList_Success() = runBlocking {
        // Prepare the data
        val expectedList = listOf("name1", "name2", "name3")
        val expectedString = expectedList.joinToString(" ")

        // When the get method is called on the localDSProvider, return the expectedString
        whenever(localDSProvider.get(StorageKeyEnum.NAMES, "")).thenReturn(expectedString)

        // Call the getList method
        val result = localDataSource.getList()

        // Verify that the get method was called with the expected arguments
        verify(localDSProvider).get(StorageKeyEnum.NAMES, "")

        // Assert that the result is equal to the expectedList
        assertEquals(expectedList, result)
    }


    @Test
    fun getList_NullReturned() = runBlocking {
        `when`(localDSProvider.get(StorageKeyEnum.NAMES, "")).thenReturn(null)

        val retrievedList = localDataSource.getList()

        assert(retrievedList.isEmpty())
    }



}