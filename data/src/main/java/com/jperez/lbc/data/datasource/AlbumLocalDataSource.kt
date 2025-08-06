package com.jperez.lbc.data.datasource

import com.jperez.lbc.data.model.AlbumATO

interface AlbumLocalDataSource {

    /**
     * Retrieves a list of albums from the database based on the provided seed, page, and page size.
     *
     * @param page The page number for pagination.
     * @return A list of AlbumATO objects or null if no albums are found.
     */
    suspend fun getAlbumsFromDatabase(
        page: Int,
    ): List<AlbumATO>?

    /**
     * Saves a list of albums to the database.
     *
     * @param albums The list of [Albu@mATO] objects to be saved.
     */
    suspend fun saveAlbumsToDatabase(
        albums: List<AlbumATO>
    )

}