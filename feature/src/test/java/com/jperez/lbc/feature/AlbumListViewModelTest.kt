package com.jperez.lbc.feature

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.jperez.lbc.domain.usecase.GetAlbumsUseCase
import com.jperez.lbc.feature.viewmodel.AlbumListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

/**
 * Unit test for [AlbumListViewModel].
 */

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumListViewModelTest : KoinTest {
    private lateinit var mockGetAlbumsUseCase: GetAlbumsUseCase
    private lateinit var viewModel: AlbumListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        mockGetAlbumsUseCase = mockk(relaxed = true)
        viewModel = AlbumListViewModel()
        startKoin {
            modules(
                module {
                    single<GetAlbumsUseCase> { mockGetAlbumsUseCase }
                })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getAlbums when there are result items are not empty`() =
        runTest(UnconfinedTestDispatcher()) {
            val flow = flowOf(PagingData.from(listOf(FeatureMockConstants.album)))
            coEvery { mockGetAlbumsUseCase.execute() } returns flow
            viewModel.getAlbums()
            advanceUntilIdle() // Yields to perform the registrations

            assertEquals(1, flow.asSnapshot { }.size)
        }
}
