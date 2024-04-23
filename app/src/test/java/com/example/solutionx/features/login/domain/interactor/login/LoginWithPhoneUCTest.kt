package com.example.solutionx.features.login.domain.interactor.login

import android.accounts.NetworkErrorException
import com.example.solutionx.common.data.model.Resource
import com.example.solutionx.common.data.model.exception.LeonException
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.ILoginRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.internal.wait
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.security.auth.login.LoginException
import org.mockito.kotlin.*

/*
- validation
 */
class LoginWithPhoneUCTest {

    private lateinit var repository: ILoginRepository
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
        val loginRequest = LoginRequest(phone = phone, password = "123456789")

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
    fun `test failed login due to invalid credentials`() = runBlockingTest {
        // Arrange
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = LoginRequest(phone = phone, password = "invalid")
        val mockedLoginRepo = mock<ILoginRepository>()
        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)

        // Act
        var result: Resource<User>? = null
        loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) {
            result = it
        }

        // Assert
        assertTrue(result is Resource.Failure)
        assertEquals("Invalid credentials", (result as Resource.Failure))
        coVerify(exactly = 0) {
            mockedLoginRepo.loginWithPhone(any())
            mockedLoginRepo.saveUser(any())
            mockedLoginRepo.saveAccessToken(any())
            mockedLoginRepo.getUser()
        }
    }

    @Test
    fun `test failed login due to network error`() = runBlockingTest {
        // Arrange
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = LoginRequest(phone = phone, password = "123456789")
        val mockedLoginRepo = mock<ILoginRepository> {
            onBlocking { loginWithPhone(loginRequest) } doThrow NetworkErrorException("Network error")
        }
        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)

        // Act
        val result = loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) { }

        // Assert
        assertTrue(result is Resource.Failure)
        assertEquals("Network error", (result as Resource.Failure))
        coVerify(exactly = 0) {
            mockedLoginRepo.saveUser(any())
            mockedLoginRepo.saveAccessToken(any())
            mockedLoginRepo.getUser()
        }
    }

    @Test
    fun `test failed login due to server error`() = runBlockingTest {
        // Arrange
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = LoginRequest(phone = phone, password = "123456789")
        val mockedLoginRepo = mock<ILoginRepository> {
            onBlocking { loginWithPhone(loginRequest) }// doThrow ServerErrorException("Server error")
        }
        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)

        // Act
        val result = loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) { }

        // Assert
        assertTrue(result is Resource.Failure)
        assertEquals("Server error", (result as Resource.Failure))
        coVerify(exactly = 0) {
            mockedLoginRepo.saveUser(any())
            mockedLoginRepo.saveAccessToken(any())
            mockedLoginRepo.getUser()
        }
    }

    @Test
    fun `test failed login due to unknown error`() = runBlockingTest {
        // Arrange
        val phone = Phone(countryCode = "0020", number = "100100100")
        val loginRequest = LoginRequest(phone = phone, password = "123456789")
        val mockedLoginRepo = mock<ILoginRepository> {
            onBlocking { loginWithPhone(loginRequest) } doThrow RuntimeException("Unknown error")
        }
        val loginWithPhoneUC = LoginWithPhoneUC(mockedLoginRepo)

        // Act
        val result = loginWithPhoneUC(CoroutineScope(Dispatchers.Default), loginRequest) { }

        // Assert
        assertTrue(result is Resource.Failure)
        assertEquals("Unknown error", (result as Resource.Failure))
        coVerify(exactly = 0) {
            mockedLoginRepo.saveUser(any())
            mockedLoginRepo.saveAccessToken(any())
            mockedLoginRepo.getUser()
        }
    }

}