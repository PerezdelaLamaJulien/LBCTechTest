package com.jperez.lbc.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jperez.lbc.data.model.AlbumATO
import com.jperez.lbc.data.paging.AlbumPagingSource
import kotlinx.coroutines.flow.Flow

class AlbumRepositoryImpl : AlbumRepository {
    override suspend fun getAlbums(): Flow<PagingData<AlbumATO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                initialLoadSize = 50,
                prefetchDistance = 10
                ),
            pagingSourceFactory = {
                AlbumPagingSource()
            }
        ).flow
    }
}
