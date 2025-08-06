package com.jperez.lbc.data.repository

import androidx.paging.PagingData
import com.jperez.lbc.data.model.AlbumATO
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    /**
     * Get a list of album from the datasource.
     *
     * @return A list of [AlbumATO] objects.
     */
    suspend fun getAlbums(): Flow<PagingData<AlbumATO>>
}