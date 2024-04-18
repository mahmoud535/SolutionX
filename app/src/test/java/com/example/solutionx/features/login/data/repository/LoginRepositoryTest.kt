package com.example.solutionx.features.login.data.repository

import com.example.solutionx.features.login.data.mapper.LoginMapper
import com.example.solutionx.features.login.data.mapper.UserMapper
import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.dto.PhoneDto
import com.example.solutionx.features.login.data.model.dto.UserDto
import com.example.solutionx.features.login.data.model.entity.UserEntity
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import com.example.solutionx.features.login.domain.model.Login
import com.example.solutionx.features.login.domain.model.User
import com.example.solutionx.features.login.domain.repository.local.ILoginLocalDS
import com.example.solutionx.features.login.domain.repository.remote.ILoginRemoteDS
import org.junit.jupiter.api.Assertions.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
class LoginRepositoryTest{
    private val remoteDs = mockk<ILoginRemoteDS>()
    private val localDs = mockk<ILoginLocalDS>()

    private val loginRepository = LoginRepository(remoteDs, localDs)

    @Test
    fun `test loginWithPhone`() {
        val phoneNumber = Phone("123456789", "0020")
        val loginRequest = LoginRequest(
            phone = phoneNumber,
            password = "123456789"
        )

        val userDto = UserDto(
            id = 1,
            username = "jdoe",
            firstname = "John",
            lastname = "Doe",
            email = "jdoe@example.com",
            phone = PhoneDto("0020", "123456789"),
            birthdate = "1990-01-01"
        )
        val loginDto = LoginDto(
            message = "Success",
            token = "token123",
            user = userDto
        )


        val expectedLogin = LoginMapper.dtoToDomain(loginDto)
        // Mock the remoteDs.loginWithPhone method to return the expected LoginDto
        coEvery { remoteDs.loginWithPhone(loginRequest) } returns  loginDto
        // Act
        val actualLogin: Login
        runBlocking {
            actualLogin = loginRepository.loginWithPhone(loginRequest)
        }

        // Assert
        assertEquals(expectedLogin, actualLogin)
        coVerify { remoteDs.loginWithPhone(loginRequest) }
    }

    @Test
    fun `test saveUser`() {
        val user = User()
        val userEntity = UserMapper.domainToEntity(user)

        // Mock the behavior of localDs
        coEvery { localDs.saveUser(userEntity) } just runs

        // Run the test
        runBlocking {
            loginRepository.saveUser(user)
        }

        // Verify that the saveUser function of localDs was called with the correct argument
        coVerify { localDs.saveUser(userEntity) }
    }


    @Test
    fun `test saveAccessToken`() {
        // Arrange
        val token = "Token123"
        coEvery { localDs.saveAccessToken(token) } returns Unit

        // Act
        runBlocking {
            loginRepository.saveAccessToken(token)
        }

        // Assert
        coVerify { localDs.saveAccessToken(token) }
    }

    @Test
    fun `test getUser`() {
        // Arrange
        val userEntity = UserEntity(
            id = 1,
            userName = "jdoe",
            firsName = "John",
            middleName = "M",
            lastName = "Doe",
            phone = "0020-123456789",
            birthDate = "1990-01-01",
            email = "jdoe@example.com"
        )

        // Set up the mock to return the expected UserEntity
        coEvery { localDs.getUser() } returns userEntity

        // Act
        val actualUserEntity: UserEntity
        runBlocking {
            actualUserEntity = loginRepository.getUser()
        }

        // Assert
        assertEquals(userEntity, actualUserEntity, "The retrieved user entity should match the expected user entity.")

        // Verify that the local data source's getUser method was called
        coVerify { localDs.getUser() }
    }

}