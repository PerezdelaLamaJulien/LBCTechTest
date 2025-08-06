package com.jperez.lbc.data

import com.jperez.lbc.data.mapper.AlbumAtoEntityMapper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.KoinTest

/**
 * Unit tests for the AlbumAtoEntityMapper class.
 */
class AlbumAtoEntityMapperTest : KoinTest {
    private val mapper: AlbumAtoEntityMapper = AlbumAtoEntityMapper()

    @Test
    fun `map ATO to Entity`() = runTest {
        val result = mapper.mapATOToEntity(
            DataMockConstants.albumATO,
        )
        assertEquals(DataMockConstants.albumEntity, result)
    }

    @Test
    fun `map Entity to ATO`() = runTest {
        val result = mapper.mapEntityToATO(
            DataMockConstants.albumEntity,
        )
        assertEquals(DataMockConstants.albumATO, result)
    }
}