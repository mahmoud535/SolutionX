package com.example.solutionx.features.login.data.repository.remote


import com.example.solutionx.features.signup.data.model.dto.SignupDto
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.signup.data.model.request.Phone
import com.example.solutionx.features.signup.data.repository.remote.SignupRemoteDS
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

//import kotlin.test.assertFailsWith


/*
test cases
1. login with valid phone returns expected result
2. test login with network error
3. loginWithPhoneReturnsNullWhenProviderReturnsNull
* */
class RemoteDataSourceTest {

    private lateinit var provider: FakeRestApiNetworkProvider
    private lateinit var remoteDataSource: SignupRemoteDS

    @Before
    fun setUp() {
        provider = FakeRestApiNetworkProvider()
        remoteDataSource = SignupRemoteDS(provider)
    }

    @Test
    fun loginWithPhoneReturnsExpectedResult() = runTest {
        val loginRequest = SignupRequest(Phone("002", "100100100"), "testCode")
        val expectedResponse = SignupDto("hi","testToken", null)

        provider.postResponse = expectedResponse

        val result = remoteDataSource.signupWithPhone(loginRequest)

        assertEquals(expectedResponse, result)
    }


    @Test
    fun loginWithPhoneReturnsNullWhenProviderReturnsNull() = runTest {
        val loginRequest = SignupRequest(Phone("002", "100100100"), "testCode")
        val expectedResponse = null

        provider.postResponse = expectedResponse

        val result = remoteDataSource.signupWithPhone(loginRequest)

        assertEquals(expectedResponse, result)
        assertNull(result)
    }


}