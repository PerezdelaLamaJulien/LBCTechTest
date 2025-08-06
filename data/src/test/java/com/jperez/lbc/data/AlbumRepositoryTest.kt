package com.jperez.lbc.data

import androidx.paging.testing.asSnapshot
import com.jperez.lbc.data.datasource.AlbumLocalDataSource
import com.jperez.lbc.data.datasource.AlbumRemoteDataSource
import com.jperez.lbc.data.repository.AlbumRepository
import com.jperez.lbc.data.repository.AlbumRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumRepository class.
 */
class AlbumRepositoryTest : KoinTest {
    private lateinit var mockAlbumRemoteDataSource: AlbumRemoteDataSource
    private lateinit var mockAlbumLocalDataSource: AlbumLocalDataSource
    private lateinit var repository: AlbumRepository

    @Before
    fun setUp() {
        mockAlbumRemoteDataSource = mockk(relaxed = true)
        mockAlbumLocalDataSource = mockk(relaxed = true)
        startKoin {
            modules(
                module {
                    single<AlbumRemoteDataSource> { mockAlbumRemoteDataSource }
                    single<AlbumLocalDataSource> { mockAlbumLocalDataSource }
                })
        }
        repository = AlbumRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get albums from remote data source`() = runTest {
        coEvery { mockAlbumLocalDataSource.getAlbumsFromDatabase(page= 1) } returns null
        coEvery { mockAlbumRemoteDataSource.getAlbums() } returns listOf(DataMockConstants.albumATO)

        val result = repository.getAlbums()

        assertEquals(DataMockConstants.albumATO, result.asSnapshot {  }.first())
    }
}