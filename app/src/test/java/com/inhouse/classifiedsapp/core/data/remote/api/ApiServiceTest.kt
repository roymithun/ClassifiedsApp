package com.inhouse.classifiedsapp.core.data.remote.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.inhouse.classifiedsapp.core.data.remote.ApiService
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import com.inhouse.classifiedsapp.core.model.ClassifiedAdList
import com.inhouse.classifiedsapp.utils.CoroutineTestRule
import com.inhouse.classifiedsapp.utils.MockResponseFileReader
import com.inhouse.classifiedsapp.utils.SUCCESS_RESPONSE_FILENAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class ApiServiceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private var mockWebServer = MockWebServer()
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `fetch successful classifiedsAds list and assert first classifiedAd uid exists`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(SUCCESS_RESPONSE_FILENAME).content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val classifiedsAdsList: Response<ClassifiedAdList> =
                apiService.getClassifiedsList()
            assertThat(classifiedsAdsList.isSuccessful).isTrue()
            assertThat((classifiedsAdsList.body()?.results?.first() as ClassifiedAd).uid).isEqualTo(
                "4878bf592579410fba52941d00b62f94"
            )
        }
    }

    @Test
    fun `fetch successful classifiedsAds list and assert first classifiedAd uid doesn't exists`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(SUCCESS_RESPONSE_FILENAME).content)

        mockWebServer.enqueue(response)
        runBlocking(coroutineTestRule.testDispatcher) {
            val classifiedsAdsList: Response<ClassifiedAdList> =
                apiService.getClassifiedsList()
            assertThat(classifiedsAdsList.isSuccessful).isTrue()
            assertThat(classifiedsAdsList.body()?.results?.map { item -> item.uid }).doesNotContain(
                "11878bf592579410fba52941d00b62f4"
            )
        }
    }
}