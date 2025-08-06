package com.jperez.lbc.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jperez.lbc.data.model.entity.AlbumEntity

@Dao
interface AlbumDao {

    /**
     * Retrieves a list of AlbumEntity objects based on the provided pagination info key.
     *
     * @param albumId Id of album meaning the page of pagination.
     * @return A list of AlbumEntity objects that match the pagination info key.
     */
    @Query("SELECT * FROM albumentity WHERE album_id LIKE :albumId")
    suspend fun findByPaginationInfo(albumId : String): List<AlbumEntity>

    /**
     * Inserts a AlbumEntity object into the database.
     *
     * @param albumEntity The AlbumEntity object to be inserted.
     */
    @Insert
    suspend fun insertAll(vararg albumEntity: AlbumEntity)
}