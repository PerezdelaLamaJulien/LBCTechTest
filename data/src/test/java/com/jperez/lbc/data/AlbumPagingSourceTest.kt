package com.jperez.lbc.data

import androidx.paging.PagingSource
import com.jperez.lbc.data.datasource.AlbumLocalDataSource
import com.jperez.lbc.data.datasource.AlbumRemoteDataSource
import com.jperez.lbc.data.paging.AlbumPagingSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumPagingSource class.
 */

class AlbumPagingSourceTest : KoinTest {
    private lateinit var mockAlbumRemoteDataSource: AlbumRemoteDataSource
    private lateinit var mockAlbumLocalDataSource: AlbumLocalDataSource
    private val pageLoadSize = 50

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
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `test item loaded with refresh from locale database`() = runTest {
        coEvery {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        } returns listOf(DataMockConstants.albumATO)

        val pagingSource = AlbumPagingSource()

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Page)
        assertEquals(
            DataMockConstants.albumATO,
            (actualLoadResult as PagingSource.LoadResult.Page).data.first()
        )
        assertEquals(2, actualLoadResult.nextKey)
        coVerify(exactly = 0) {
            mockAlbumRemoteDataSource.getAlbums()
        }

        coVerify(exactly = 1) {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        }
    }

    @Test
    fun `test item loaded with refresh from remote datasource`() = runTest {
        coEvery {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        } returns null

        coEvery {
            mockAlbumRemoteDataSource.getAlbums(
            )
        } returns listOf(DataMockConstants.albumATO)


        val pagingSource = AlbumPagingSource()

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Page)
        assertEquals(
            DataMockConstants.albumATO,
            (actualLoadResult as PagingSource.LoadResult.Page).data.first()
        )
        assertEquals(2, actualLoadResult.nextKey)
        coVerify(exactly = 1) {
            mockAlbumRemoteDataSource.getAlbums()
        }
        coVerify(exactly = 1) {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        }
        coVerify(exactly = 1) {
            mockAlbumLocalDataSource.saveAlbumsToDatabase(
                albums = listOf(DataMockConstants.albumATO)
            )
        }
    }

    @Test
    fun `load result error with database exception`() = runTest {
        coEvery {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        } throws Exception("Database error")

        val pagingSource = AlbumPagingSource()

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)

        assertTrue(actualLoadResult is PagingSource.LoadResult.Error)
        coVerify(exactly = 0) {
            mockAlbumRemoteDataSource.getAlbums()
        }

        coVerify(exactly = 1) {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        }
    }

    @Test
    fun `load result error with http exception`() = runTest {
        coEvery {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        } returns null

        coEvery {
            mockAlbumRemoteDataSource.getAlbums()
        } throws Exception("network error")


        val pagingSource = AlbumPagingSource()

        val refreshLoadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = pageLoadSize,
            placeholdersEnabled = false
        )

        val actualLoadResult = pagingSource.load(refreshLoadParams)
        assertTrue(actualLoadResult is PagingSource.LoadResult.Error)

        coVerify(exactly = 1) {
            mockAlbumRemoteDataSource.getAlbums()
        }
        coVerify(exactly = 1) {
            mockAlbumLocalDataSource.getAlbumsFromDatabase(
                page = 1,
            )
        }
    }
}