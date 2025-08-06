package com.jperez.lbc.data

import com.jperez.lbc.data.model.AlbumATO
import com.jperez.lbc.data.model.entity.AlbumEntity

class DataMockConstants {
    companion object {
        val albumATO = AlbumATO(
            id = 1,
            albumId = 1,
            title= "accusamus beatae ad facilis cum similique qui sunt",
            url=  "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png",
            )


        val albumEntity = AlbumEntity(
            id = 1,
            albumId = 1,
            title= "accusamus beatae ad facilis cum similique qui sunt",
            url=  "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png",
        )
    }
}