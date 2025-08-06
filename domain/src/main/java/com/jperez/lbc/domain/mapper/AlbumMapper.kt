package com.jperez.lbc.domain.mapper

import com.jperez.lbc.data.model.AlbumATO
import com.jperez.lbc.domain.model.Album

/**
 * Mapper class to convert [AlbumATO] to [Album].
 */
class AlbumMapper {

    /**
     * Map a [AlbumATO] object to a [Album] object.
     *
     * @param ato The [AlbumATO] object to be mapped.
     * @return A [Album] object with the mapped values.
     */
    fun mapTo(ato: AlbumATO): Album =
        Album(
            id = ato.id,
            title = ato.title,
            url = ato.url,
            thumbnailUrl = ato.thumbnailUrl,
        )
}