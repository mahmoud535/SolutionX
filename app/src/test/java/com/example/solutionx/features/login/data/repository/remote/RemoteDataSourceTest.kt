package com.example.solutionx.features.login.data.repository.remote


import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class RemoteDataSourceTest {

//    @MockK
//    private lateinit var provider: FakeRestApiNetworkProvider
////    private lateinit var remoteDataSource: LoginRemoteDS
//
//    @Before
//    fun setUp() {
////        provider = FakeRestApiNetworkProvider()
////        remoteDataSource = LoginRemoteDS(provider)
//    }
//
//    @Test
//    fun loginWithPhoneReturnsExpectedResult() = runTest {
//        val loginRequest = LoginRequest(Phone("002","100100100"), "testCode")
//        val expectedResponse = LoginDto("testToken", null)
//
//        provider.post<>() = expectedResponse
//
//        val result = remoteDataSource.loginWithPhone(loginRequest)
//
//        assertEquals(expectedResponse, result)
//    }
}