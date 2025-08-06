package com.jperez.lbc.feature

import com.jperez.lbc.feature.mapper.AlbumListItemUIMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumListItemUIMapper class.
 */
class AlbumListItemUIMapperTest : KoinTest {
    private val mapper = AlbumListItemUIMapper()

    @Test
    fun `map album to AlbumListItemUI`() = runTest {
        val result = mapper.mapTo(FeatureMockConstants.album)
        assertEquals(FeatureMockConstants.albumListItemUI, result)
    }
}