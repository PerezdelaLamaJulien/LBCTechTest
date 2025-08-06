package com.jperez.lbc.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jperez.lbc.data.datasource.AlbumLocalDataSource
import com.jperez.lbc.data.datasource.AlbumRemoteDataSource
import com.jperez.lbc.data.model.AlbumATO
import org.koin.java.KoinJavaComponent.inject

/**
 * A [PagingSource] implementation for loading albums from a data source.
 */

class AlbumPagingSource: PagingSource<Int, AlbumATO>() {
    private val remoteDataSource: AlbumRemoteDataSource by inject(
        AlbumRemoteDataSource::class.java)
    private val localDataSource: AlbumLocalDataSource by inject(
        AlbumLocalDataSource::class.java)

    /**
     * Loads a page of albums from the remote data source or local cache.
     * If the albums are not available in the local cache,
     * it fetches them from the remote data source and saves them to the local cache.
     *
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumATO> {
        return try {
            val currentPage = params.key ?: 1

            val cachedAlbums = localDataSource.getAlbumsFromDatabase(
                page = currentPage,
            )
            if(!cachedAlbums.isNullOrEmpty()){
                LoadResult.Page(
                    data = cachedAlbums,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (cachedAlbums.isEmpty()) null else currentPage + 1
                )
            } else {
                val response = remoteDataSource.getAlbums()
                localDataSource.saveAlbumsToDatabase(
                    albums = response
                )
                LoadResult.Page(
                    data = response.filter { ato -> ato.albumId == currentPage },
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (response.isEmpty()) null else currentPage + 1
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AlbumATO>): Int? {
        return state.anchorPosition
    }
}