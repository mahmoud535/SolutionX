package com.example.solutionx.features.login.data.repository

import com.example.solutionx.features.login.data.mapper.LoginMapper
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.dto.PhoneDto
import com.example.solutionx.features.login.data.model.dto.UserDto
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever


class LoginRepositoryTest {
    private lateinit var remoteDs: ILoginRemoteDS
    private lateinit var localDs: ILoginLocalDS
    private lateinit var repository: LoginRepository

    @Before
    fun setUp() {
        remoteDs = Mockito.mock(ILoginRemoteDS::class.java)
        localDs = Mockito.mock(ILoginLocalDS::class.java)
        repository = LoginRepository(remoteDs, localDs)
    }

    @Test
    fun testLoginWithPhone() {
        runBlocking {
            val phoneNumber = Phone("0020", "100100100")
            val loginRequest = LoginRequest(phone = phoneNumber, password = "123456789")
            val user = UserDto(
                id = 1,
                username = "jdoe",
                firstname = "John",
                lastname = "Doe",
                email = "jdoe@example.com",
                phone = PhoneDto("0020", "123456789"),
                birthdate = "1990-01-01"
            )
            val loginResponse = LoginDto("", "mnsnln2356", user)

            // Mock remote data source behavior
            whenever(remoteDs.loginWithPhone(loginRequest)).thenReturn(loginResponse)

            // Call the method to be tested
            val result = repository.loginWithPhone(loginRequest)

            // Verify interactions and assertions
            verify(remoteDs).loginWithPhone(loginRequest)
            assertEquals(LoginMapper.dtoToDomain(loginResponse), result)
        }
    }

    @Test
    fun testSaveUser() {
        runBlocking {
            val user = User()
            val userEntity = UserMapper.domainToEntity(user)

            // Call the method to be tested
            repository.saveUser(user)

            // Verify interactions
            verify(localDs).saveUser(userEntity)
        }
    }

    @Test
    fun testSaveAccessToken() = runBlocking{
        val token = "sampleToken"

        // Call the method to be tested
        repository.saveAccessToken(token)

        // Verify interactions
        verify(localDs).saveAccessToken(token)
    }

    @Test
    fun testGetUser() = runBlocking{
        val userEntity = UserEntity()

        // Mock local data source behavior
        whenever(localDs.getUser()).thenReturn(userEntity)

        val result = repository.getUser()

        // Verify interactions and assertions
        verify(localDs).getUser()
        assertEquals(userEntity, result)
    }

}

