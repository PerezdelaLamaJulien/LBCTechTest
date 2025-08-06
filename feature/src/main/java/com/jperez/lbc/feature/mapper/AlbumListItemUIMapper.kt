package com.jperez.lbc.feature.mapper

import com.jperez.lbc.domain.model.Album
import com.jperez.lbc.feature.model.AlbumListItemUI

/**
 * Mapper class to convert [Album] to a [AlbumListItemUI].
 */
class AlbumListItemUIMapper {

    /**
     * Map a [Album] object to a [AlbumListItemUI] object.
     *
     * @param album The [Album] object to be mapped.
     * @return A [AlbumListItemUI] object with the mapped values.
     */

    fun mapTo(album: Album): AlbumListItemUI =
        AlbumListItemUI(
            title = album.title,
            imageUrl = album.thumbnailUrl,
        )
}