package com.jperez.lbc.data

import com.jperez.lbc.data.database.AlbumDao
import com.jperez.lbc.data.datasource.AlbumLocalDataSource
import com.jperez.lbc.data.datasource.AlbumLocalDataSourceImpl
import com.jperez.lbc.data.mapper.AlbumAtoEntityMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumLocalDataSource class.
 */
class AlbumLocalDataSourceTest : KoinTest {
    private lateinit var mockAlbumDao: AlbumDao
    private lateinit var mockAlbumAtoEntityMapper: AlbumAtoEntityMapper
    private lateinit var localDataSource: AlbumLocalDataSource

    @Before
    fun setUp() {
        mockAlbumDao = mockk(relaxed = true)
        mockAlbumAtoEntityMapper = mockk(relaxed = true)
        localDataSource = AlbumLocalDataSourceImpl()

        startKoin {
            modules(
                module {
                    single<AlbumDao> { mockAlbumDao }
                    single<AlbumAtoEntityMapper> { mockAlbumAtoEntityMapper }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `paginationInfo is save so get function return list of album`() = runTest {
        coEvery { mockAlbumDao.findByPaginationInfo("1") } returns listOf(DataMockConstants.albumEntity)
        coEvery { mockAlbumAtoEntityMapper.mapEntityToATO(DataMockConstants.albumEntity) } returns DataMockConstants.albumATO

        val result = localDataSource.getAlbumsFromDatabase(page = 1)
        assertEquals(DataMockConstants.albumATO, result?.first())
    }

    @Test
    fun `paginationInfo findByInfo throw exception return null`() = runTest {
        coEvery {
            mockAlbumDao.findByPaginationInfo("1")
        } throws Exception("Database error")

        val result = localDataSource.getAlbumsFromDatabase(page = 1)
        assertNull(result)
    }

    @Test
    fun `paginationInfo save call daos`() = runTest {
        coEvery { mockAlbumDao.insertAll(any()) } answers {}
        coEvery {
            mockAlbumAtoEntityMapper.mapATOToEntity(
                DataMockConstants.albumATO
            )
        } returns DataMockConstants.albumEntity
        localDataSource.saveAlbumsToDatabase(
            albums = listOf(DataMockConstants.albumATO)
        )

        coVerify {
            mockAlbumDao.insertAll(any())
        }
    }
}