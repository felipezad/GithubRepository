package com.example.lookatxing

import com.example.domain.GitHubResponseItem
import com.example.lookatxing.data.remote.XingService
import com.example.util.WhenNullReturnEmptyFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class XingServiceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var service: XingService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(WhenNullReturnEmptyFactory)
            .addLast(KotlinJsonAdapterFactory())
            .build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
            .create(XingService::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }

    @Test
    fun `Request JSON from service and convert to object through Moshi`() {
        runBlocking {
            enqueueResponse("api_mock.json")
            val apiResponse: List<GitHubResponseItem> = service.requestRepos()

            assertNotNull(apiResponse)
            assertEquals(234107, apiResponse[0].id)
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}