package com.jperez.lbc.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.jperez.lbc.data.repository.AlbumRepository
import com.jperez.lbc.domain.mapper.AlbumMapper
import com.jperez.lbc.domain.model.Album
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent.inject

/**
 * Use case to retrieve a list of albums.
 *
 * This use case interacts with the [AlbumRepository] to fetch albums
 * and maps them to the domain model using [AlbumMapper].
 *
 * @property repository The repository to fetch album from.
 * @property mapper The mapper to convert data model to domain model.
 */
class GetAlbumsUseCase {
    private val repository: AlbumRepository by inject(AlbumRepository::class.java)
    private val mapper: AlbumMapper by inject(AlbumMapper::class.java)

    /**
     * Retrieves a list of album based on the provided seed.
     *
     * @return A list of [Album] objects.
     */
    suspend fun execute(): Flow<PagingData<Album>> {
        val albums = repository.getAlbums()
        return albums.map { pagingData ->
            pagingData.map { album -> mapper.mapTo(album) }
        }
    }
}