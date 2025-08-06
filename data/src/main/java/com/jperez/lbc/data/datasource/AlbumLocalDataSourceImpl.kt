package com.jperez.lbc.data.datasource

import com.jperez.lbc.data.database.AlbumDao
import com.jperez.lbc.data.mapper.AlbumAtoEntityMapper
import com.jperez.lbc.data.model.AlbumATO
import org.koin.java.KoinJavaComponent.inject

class AlbumLocalDataSourceImpl : AlbumLocalDataSource {
    private val albumDao: AlbumDao by inject(AlbumDao::class.java)
    private val mapper: AlbumAtoEntityMapper by inject(AlbumAtoEntityMapper::class.java)

    override suspend fun getAlbumsFromDatabase(page: Int): List<AlbumATO>? {
        try {
            val entities = albumDao.findByPaginationInfo(page.toString())
            return entities.map { mapper.mapEntityToATO(it) }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun saveAlbumsToDatabase(albums: List<AlbumATO>) {
        albumDao.insertAll(
            *albums.map {
                mapper.mapATOToEntity(it)
            }.toTypedArray()
        )
    }
}
