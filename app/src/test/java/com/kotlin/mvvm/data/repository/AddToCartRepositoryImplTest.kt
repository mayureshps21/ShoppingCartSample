package com.kotlin.mvvm.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.gson.Gson
import com.kotlin.mvvm.data.api.ApiInterface
import com.kotlin.mvvm.data.dto.UserDto
import com.kotlin.mvvm.domain.repository.ValidSessionRepo
import com.kotlin.mvvm.utils.ApplicationConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class AddToCartRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryImpl: AddToCartRepositoryImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    var dispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var context: Context

    lateinit var apiInterface: ApiInterface

    @Mock
    lateinit var validSessionRepo: ValidSessionRepo
    private lateinit var mockWebServer: MockWebServer

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun SetUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        apiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)


        repositoryImpl =
            AddToCartRepositoryImpl(apiInterface, context, validSessionRepo, sharedPreferences)
        repositoryImpl.sharedPreferences = sharedPreferences

    }

    @Test
    fun addToCartSuccess() = runTest(dispatcher) {
        Mockito.`when`(validSessionRepo.checkIfUserValid())
            .thenReturn(ApplicationConstant.SESSION_VALID)
        Mockito.`when`(validSessionRepo.refreshSession())
            .thenReturn(ApplicationConstant.SESSION_REFRESHED)
        repositoryImpl.addToCart(5, "wallet", "$67", "")
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(UserDto("5","5","Abhay")))
        mockWebServer.enqueue(expectedResponse)
        val actualResponse = apiInterface.getUser("5")
        Assert.assertEquals(actualResponse.title, "Abhay")
    }

    @Test
    fun addToCartFailed() = runTest(dispatcher) {
        Mockito.`when`(validSessionRepo.checkIfUserValid())
            .thenReturn(ApplicationConstant.SESSION_INVALID)
        Mockito.`when`(validSessionRepo.refreshSession())
            .thenReturn(ApplicationConstant.SESSION_REFRESHED_FAILED)
       repositoryImpl.addToCart(5, "wallet", "$67", "").test {
           Assert.assertEquals(this.awaitItem(),ApplicationConstant.SESSION_INVALID)
           awaitComplete()
       }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}