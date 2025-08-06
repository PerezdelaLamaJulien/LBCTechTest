package com.jperez.lbc.data.datasource

import com.jperez.lbc.data.model.AlbumATO

interface AlbumRemoteDataSource {

    /**
     * Get a response from the API.
     *
     * @return A list of [AlbumATO] objects.
     */
    suspend fun getAlbums(): List<AlbumATO>
}