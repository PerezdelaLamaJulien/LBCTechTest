package com.jperez.lbc.data.mapper

import com.jperez.lbc.data.model.AlbumATO
import com.jperez.lbc.data.model.entity.AlbumEntity

class AlbumAtoEntityMapper {

    /**
     * Map a [AlbumATO] object to a [AlbumEntity] object.
     *
     * @param ato The [AlbumATO] object to be mapped.
     * @return A [AlbumEntity] object with the mapped values.
     */
     fun mapATOToEntity(ato: AlbumATO): AlbumEntity =
        AlbumEntity(
            id = ato.id,
            albumId = ato.albumId,
            url = ato.url,
            thumbnailUrl = ato.thumbnailUrl,
            title = ato.title,
        )

    /**
     * Map a [AlbumEntity] object to a [AlbumATO] object.
     *
     * @param entity The [AlbumEntity] object to be mapped.
     * @return A [AlbumATO] object with the mapped values.
     */
     fun mapEntityToATO(entity: AlbumEntity): AlbumATO {
        return AlbumATO(
            id = entity.id,
            albumId = entity.albumId,
            url = entity.url,
            thumbnailUrl = entity.thumbnailUrl,
            title = entity.title,
        )
    }
}