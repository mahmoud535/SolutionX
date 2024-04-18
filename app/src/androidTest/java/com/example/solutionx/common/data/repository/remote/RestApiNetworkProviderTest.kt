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

}