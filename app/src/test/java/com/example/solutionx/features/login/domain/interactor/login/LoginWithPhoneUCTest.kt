//package com.example.solutionx.features.login.domain.interactor.login
//
//import com.example.solutionx.common.data.model.Resource
//import com.example.solutionx.features.login.data.model.request.LoginRequest
//import com.example.solutionx.features.login.domain.model.User
//import com.example.solutionx.features.login.domain.repository.ILoginRepository
//import kotlinx.coroutines.*
//import kotlinx.coroutines.test.runTest
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//
//class LoginWithPhoneUCTest {
//
//    @Test
//    operator fun invoke() {
//    }
//
//    @Test
//    fun `test successful login with specific phone number, password, and country code`() = runTest {
//        // Arrange
//        val phoneNumber = "100100100" // Specify the phone number
//        val password = "123456789" // Specify the password
//        val countryCode = "0020" // Specify the country code
//        val loginRequest = LoginRequest(countryCode + phoneNumber, password) // Create LoginRequest with country code and phone number
//
//        // Define expected user and resource for successful login
//        val expectedUser = User("user_id", "user_name", accessToken = "access_token_value")
//        val expectedResource = Resource.Success(expectedUser)
//
//        // Mock repository methods for successful login
//        `when`(repository.loginWithPhone(loginRequest)).thenReturn(expectedResource)
//        `when`(repository.getUser()).thenReturn(expectedUser)
//
//        // Capture the result
//        var result: Resource<User>? = null
//
//        // Act
//        loginWithPhoneUC(this, loginRequest) { res ->
//            result = res
//        }
//
//        // Assert
//        assertNotNull(result)
//        assertEquals(expectedResource, result)
//
//        // Verify that repository methods were called as expected
//        verify(repository).loginWithPhone(loginRequest)
//        verify(repository).saveUser(expectedUser)
//        verify(repository).saveAccessToken(expectedUser.accessToken)
//        verify(repository).getUser()
//
//}