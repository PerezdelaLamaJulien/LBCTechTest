package com.jperez.lbc.domain

import com.jperez.lbc.domain.mapper.AlbumMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumMapper class.
 */
class AlbumMapperTest : KoinTest {
    private val mockAlbumMapper: AlbumMapper = AlbumMapper()

    /**
     * Tests the mapping of a AlbumATO object to a Album object.
     * It verifies that the properties of the Album object match the expected values.
     */
    @Test
    fun `map AlbumATO to Album`() = runTest {
        val result = mockAlbumMapper.mapTo(DomainMockConstants.albumATO)
        assertEquals(DomainMockConstants.album, result)
    }
}