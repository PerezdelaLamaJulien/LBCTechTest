package com.jperez.lbc.domain

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jperez.lbc.data.repository.AlbumRepository
import com.jperez.lbc.domain.mapper.AlbumMapper
import com.jperez.lbc.domain.usecase.GetAlbumsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
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
 * Unit tests for the GetAlbumsUseCase class.
 */
class GetAlbumsUseCaseTest : KoinTest {
    private lateinit var mockRepository: AlbumRepository
    private lateinit var mockAlbumMapper: AlbumMapper
    private lateinit var getAlbumsUseCase: GetAlbumsUseCase

    @Before
    fun setUp() {
        mockRepository = mockk(relaxed = true)
        mockAlbumMapper = mockk(relaxed = true)
        getAlbumsUseCase = GetAlbumsUseCase()
        startKoin {
            modules(
                module {
                    single<AlbumRepository> { mockRepository }
                    single<AlbumMapper> { mockAlbumMapper }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `get Albums`() = runTest {
        val flow = flowOf(PagingData.from(listOf(DomainMockConstants.albumATO)))

        coEvery { mockRepository.getAlbums() } returns flow
        coEvery { mockAlbumMapper.mapTo(DomainMockConstants.albumATO) } returns DomainMockConstants.album
        val result = getAlbumsUseCase.execute()
        assertEquals(1, flow.asSnapshot {  }.size)

    }
}