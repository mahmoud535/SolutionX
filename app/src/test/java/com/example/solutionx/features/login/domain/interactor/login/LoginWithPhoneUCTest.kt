package com.example.solutionx.features.login.domain.interactor.login

import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.signup.data.model.request.SignupRequest
import com.example.solutionx.features.signup.data.model.request.Phone
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.signup.domain.repository.ISignupRepository
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

/*
- validation
 */
class LoginWithPhoneUCTest {

    private lateinit var repository: ISignupRepository
    private lateinit var loginWithPhoneUC: LoginWithPhoneUC
    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp() {
        repository = mockk()
        loginWithPhoneUC = LoginWithPhoneUC(repository)
        // Set the main dispatcher to a test dispatcher
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher
        Dispatchers.resetMain()
    }

    @Test
    fun `test successful login with phone, country code , and password `() = runTest {
        // Given a valid login request with the specified phone, country code, and password
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = SignupRequest(phone = phone, password = "123456789")

        // Define a user that is expected to be returned by the repository
        val user = User(
            id = 1,
            userName = "jdoe",
            fullName = "John Doe",
            email = "jdoe@example.com",
            phone = phone.number
        )

        // Define the login object to be returned by the repository
        val login = Login(message = "Success", accessToken = "token123", userInfo = user)

        // Mock the repository behavior
        coEvery { repository.loginWithPhone(loginRequest) } returns login
        coEvery { repository.saveUser(user) } just Runs
        coEvery { repository.saveAccessToken(login.accessToken) } just Runs
        coEvery { repository.getUser() } returns UserMapper.domainToEntity(user)

        // When invoking the use case
        var result: Resource<User>? = null
        loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) {
            result = it
        }

        // Wait for the coroutine to complete
        advanceUntilIdle()

        // Then the result should be successful with the expected user
        assertNotNull(result)
        println("result: $result")
        println("result class: ${result?.javaClass?.simpleName}")
        assertTrue(result is Resource.Success<User>)
        assertEquals(user, (result as Resource.Success<User>).model)

        // Verify repository interactions
        coVerify { repository.loginWithPhone(loginRequest) }
        coVerify { repository.saveUser(user) }
        coVerify { repository.saveAccessToken(login.accessToken) }
        coVerify { repository.getUser() }
    }




    @Test
    fun `test network error during login`() = runTest {
        // Given a login request
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = SignupRequest(phone = phone, password = "valid_password")
        val networkError = LeonException.Network.Unhandled(messageRes = 0, message = "Network error")

        // Mock the repository behavior to throw a network error
        coEvery { repository.loginWithPhone(loginRequest) } throws networkError

        // When invoking the use case
        var result: Resource<User>? = null
        loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) {
            result = it
        }

        // Wait for the coroutine to complete
        advanceUntilIdle()

        // Then the result should be a failure with the expected network error
        assertThat(result).isNotNull()
        assertThat(result).isInstanceOf(Resource.Failure::class.java)
        assertThat((result as Resource.Failure).exception).isEqualTo(networkError)

        // Verify repository interactions
        coVerify { repository.loginWithPhone(loginRequest) }
        coVerify(exactly = 0) { repository.saveUser(any()) }
        coVerify(exactly = 0) { repository.saveAccessToken(any()) }
        coVerify(exactly = 0) { repository.getUser() }
    }




    //-----------------------------------------------------------------------------------------//


//    @Test
//    fun `test failed login due to network error`() = runBlockingTest {
//        // Arrange
//        val phone = Phone(countryCode = "0020", number = "100100100")
//        val loginRequest = SignupRequest(phone = phone, password = "123456789")
//        val mockedLoginRepo = mock<ISignupRepository> {
//            onBlocking { loginWithPhone(loginRequest) } doThrow NetworkErrorException("Network error")
//        }
//        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)
//
//        // Act
//        val result = loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) { }
//
//        // Assert
//        assertTrue(result is Resource.Failure)
//        assertEquals("Network error", (result as Resource.Failure))
//        coVerify(exactly = 0) {
//            mockedLoginRepo.saveUser(any())
//            mockedLoginRepo.saveAccessToken(any())
//            mockedLoginRepo.getUser()
//        }
//    }
//
//    @Test
//    fun `test failed login due to server error`() = runBlockingTest {
//        // Arrange
//        val phone = Phone(countryCode = "0020", number = "100100100")
//        val loginRequest = SignupRequest(phone = phone, password = "123456789")
//        val mockedLoginRepo = mock<ISignupRepository> {
//            onBlocking { loginWithPhone(loginRequest) }// doThrow ServerErrorException("Server error")
//        }
//        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)
//
//        // Act
//        val result = loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) { }
//
//        // Assert
//        assertTrue(result is Resource.Failure)
//        assertEquals("Server error", (result as Resource.Failure))
//        coVerify(exactly = 0) {
//            mockedLoginRepo.saveUser(any())
//            mockedLoginRepo.saveAccessToken(any())
//            mockedLoginRepo.getUser()
//        }
//    }
//
//}

//    @Test
//    fun `test failed login due to unknown error`() = runTest {
//        // Arrange
//        val phone = Phone(countryCode = "0020", number = "100100100")
//        val loginRequest = SignupRequest(phone = phone, password = "123456789")
//
//        // Mock the repository behavior to throw an unknown error
//        val mockedRepository = mockk<ISignupRepository> {
//            coEvery { loginWithPhone(loginRequest) } throws LeonException.Unknown("Unknown error")
//        }
//
//        // Create an instance of the use case with the mocked repository
//        val loginWithPhoneUC = LoginWithPhoneUC(mockedRepository)
//
//        // Act
//        var result: Resource<User>? = null
//        loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) {
//            result = it
//        }
//
//        // Assert
//        // Verify that the result is a failure with the expected error message
//        assertTrue(result is Resource.Failure)
//        assertEquals("Unknown error", (result as Resource.Failure).exception.message)
//
//        // Verify repository interactions
//        coVerify { repository.loginWithPhone(loginRequest) }
//        coVerify(exactly = 0) { repository.saveUser(any()) }
//        coVerify(exactly = 0) { repository.saveAccessToken(any()) }
//        coVerify(exactly = 0) { repository.getUser() }
//    }


    //    @Test
//    fun `test failed login due to invalid credentials`() = runTest {
//        // Given a login request with invalid credentials
//        val phone = Phone(countryCode = "0020", number = "100100100")
//        val loginRequest = SignupRequest(phone = phone, password = "invalid")
//        val exception = LeonException.Unknown("Invalid credentials")
//        val user = User(
//            id = 1,
//            userName = "jdoe",
//            fullName = "John Doe",
//            email = "jdoe@example.com",
//            phone = phone.number
//        )
//
//        // Define the login object to be returned by the repository
//        val login = Login(message = "Success", accessToken = "token123", userInfo = user)
//
//        // Mock the repository behavior to return a failure response
//        coEvery { repository.loginWithPhone(loginRequest) } returns login
//
//        // When invoking the use case
//        var result: Resource<User>? = null
//        loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) {
//            result = it
//        }
//
//        // Wait for the coroutine to complete
//        advanceUntilIdle()
//
//        // Then the result should be a failure with the expected error message
//        assertThat(result).isNotNull()
//        assertThat(result).isInstanceOf(Resource.Failure::class.java)
//        assertThat((result as Resource.Failure).exception).isEqualTo(exception)
//
//        // Verify repository interactions
//        coVerify { repository.loginWithPhone(loginRequest) }
//        coVerify(exactly = 0) { repository.saveUser(any()) }
//        coVerify(exactly = 0) { repository.saveAccessToken(any()) }
//        coVerify(exactly = 0) { repository.getUser() }
//    }


}
