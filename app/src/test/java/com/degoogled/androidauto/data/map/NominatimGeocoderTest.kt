package com.degoogled.androidauto.data.map

import android.content.Context
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import java.io.ByteArrayInputStream
import java.net.HttpURLConnection
import java.net.URL
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
@PrepareForTest(URL::class, NominatimGeocoder::class)
class NominatimGeocoderTest {

    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var geocoder: NominatimGeocoder
    private lateinit var context: Context
    private lateinit var mockConnection: HttpURLConnection
    private lateinit var mockUrl: URL
    
    @Before
    fun setup() {
        context = mock()
        mockConnection = mock()
        mockUrl = mock()
        
        PowerMockito.whenNew(URL::class.java).withAnyArguments().thenReturn(mockUrl)
        whenever(mockUrl.openConnection()).thenReturn(mockConnection)
        
        geocoder = NominatimGeocoder(context)
    }
    
    @Test
    fun `search should return locations on success`() = testDispatcher.runBlockingTest {
        // Given
        val query = "San Francisco"
        val responseJson = """
            [
                {
                    "lat": "37.7749",
                    "lon": "-122.4194",
                    "display_name": "San Francisco, CA, USA"
                },
                {
                    "lat": "37.7694",
                    "lon": "-122.4862",
                    "display_name": "Golden Gate Park, San Francisco, CA, USA"
                }
            ]
        """.trimIndent()
        
        whenever(mockConnection.responseCode).thenReturn(HttpURLConnection.HTTP_OK)
        whenever(mockConnection.inputStream).thenReturn(ByteArrayInputStream(responseJson.toByteArray()))
        
        // When
        val result = geocoder.search(query)
        
        // Then
        assert(result.isSuccess)
        val locations = result.getOrNull()
        assert(locations?.size == 2)
        assert(locations?.get(0)?.name == "San Francisco, CA, USA")
        assert(locations?.get(0)?.latitude == 37.7749)
        assert(locations?.get(0)?.longitude == -122.4194)
    }
    
    @Test
    fun `search should return failure on HTTP error`() = testDispatcher.runBlockingTest {
        // Given
        val query = "Invalid Location"
        
        whenever(mockConnection.responseCode).thenReturn(HttpURLConnection.HTTP_NOT_FOUND)
        
        // When
        val result = geocoder.search(query)
        
        // Then
        assert(result.isFailure)
    }
    
    @Test
    fun `reverseGeocode should return address on success`() = testDispatcher.runBlockingTest {
        // Given
        val latitude = 37.7749
        val longitude = -122.4194
        val responseJson = """
            {
                "display_name": "San Francisco, CA, USA"
            }
        """.trimIndent()
        
        whenever(mockConnection.responseCode).thenReturn(HttpURLConnection.HTTP_OK)
        whenever(mockConnection.inputStream).thenReturn(ByteArrayInputStream(responseJson.toByteArray()))
        
        // When
        val result = geocoder.reverseGeocode(latitude, longitude)
        
        // Then
        assert(result.isSuccess)
        val address = result.getOrNull()
        assert(address == "San Francisco, CA, USA")
    }
    
    @Test
    fun `reverseGeocode should return failure on HTTP error`() = testDispatcher.runBlockingTest {
        // Given
        val latitude = 37.7749
        val longitude = -122.4194
        
        whenever(mockConnection.responseCode).thenReturn(HttpURLConnection.HTTP_NOT_FOUND)
        
        // When
        val result = geocoder.reverseGeocode(latitude, longitude)
        
        // Then
        assert(result.isFailure)
    }
}