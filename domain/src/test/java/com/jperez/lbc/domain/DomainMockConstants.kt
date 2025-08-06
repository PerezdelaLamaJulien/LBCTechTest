package com.jperez.lbc.domain

import com.jperez.lbc.data.model.AlbumATO
import com.jperez.lbc.domain.model.Album

class DomainMockConstants {
    companion object {
        val albumATO = AlbumATO(
            id = 1,
            albumId = 1,
            title= "accusamus beatae ad facilis cum similique qui sunt",
            url=  "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png",
        )

        val album = Album(
            id = 1,
            title= "accusamus beatae ad facilis cum similique qui sunt",
            url=  "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png",
        )
    }
}