package com.example.solutionx.common.data.repository.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.solutionx.features.login.data.model.dto.LoginDto
import com.example.solutionx.features.login.data.model.request.LoginRequest
import com.example.solutionx.features.login.data.model.request.Phone
import com.example.solutionx.features.login.presentation.util.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


@RunWith(AndroidJUnit4::class)
class RestApiNetworkProviderTest {
//
//    @Test
//    fun postmethodsuccessfullogin() {
//        // Given
//        val mockServiceApi = object : ServiceApi {
//            override suspend fun post(
//                pathUrl: String, headers: Map<String, Any>,
//                queryParams: Map<String, Any>, requestBody: Any
//            ): ResponseBody {
//                // Mock the successful response body
//                val responseJson = """
//                {
//                    "message": "Success",
//                    "token": "exampleToken",
//                    "user": {
//                        "id": 1,
//                        "firstname": "John",
//                        "lastname": "Doe",
//                        "email": "john.doe@example.com"
//                    }
//                }
//                """.trimIndent()
//                return ResponseBody.create(
//                    MediaType.parse("application/json"),
//                    responseJson
//                )
//            }
//        }
//
//        // Define the expected response type
//        val responseType: Type = LoginDto::class.java
//
//        // Define input parameters for successful login
//        val pathUrl = "login"
//        val headers = mapOf("Accept-Language" to "en")
//        val queryParams = emptyMap<String, Any>()
//        val requestBody = LoginRequest(
//            Phone(countryCode = "0020", number = "100100100"),
//            password = "123456789"
//        )
//
//        // Create RestApiNetworkProvider instance with the mock ServiceApi
//        val provider = RestApiNetworkProvider(mockServiceApi)
//
//        // When
//        val response: LoginDto? = provider.post(
//            responseType,
//            pathUrl,
//            headers,
//            queryParams,
//            requestBody
//        )
//
//        // Then
//        // Verify the expected response
//        assertNotNull(response)
//        assertEquals("Success", response?.message)
//        assertEquals("exampleToken", response?.token)
//        assertNotNull(response?.user)
//        assertEquals(1, response?.user?.id)
//        assertEquals("John", response?.user?.firstname)
//        assertEquals("Doe", response?.user?.lastname)
//        assertEquals("john.doe@example.com", response?.user?.email)
//    }
}