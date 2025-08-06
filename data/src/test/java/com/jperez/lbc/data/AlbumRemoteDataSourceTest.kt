package com.jperez.lbc.data

import com.jperez.lbc.data.api.ApiClient
import com.jperez.lbc.data.datasource.AlbumRemoteDataSource
import com.jperez.lbc.data.datasource.AlbumRemoteDataSourceImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest


/**
 * Unit tests for the AlbumRemoteDataSource class.
 */
class AlbumRemoteDataSourceTest : KoinTest {
    private lateinit var mockApiClient: ApiClient
    private lateinit var remoteDataSource: AlbumRemoteDataSource

    @Before
    fun setUp() {
        mockApiClient = mockk(relaxed = true)
        remoteDataSource = AlbumRemoteDataSourceImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get albums from api`() = runTest {
        startKoin {
            modules(
                module {
                    single<ApiClient> { mockApiClient }
                })
        }
        coEvery { mockApiClient.getAlbums() } returns listOf(DataMockConstants.albumATO)

        val result = remoteDataSource.getAlbums()

        assertEquals(DataMockConstants.albumATO, result.first())
    }
}