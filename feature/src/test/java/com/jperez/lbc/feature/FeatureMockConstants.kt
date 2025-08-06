package com.jperez.lbc.feature

import com.jperez.lbc.domain.model.Album
import com.jperez.lbc.feature.model.AlbumListItemUI

class FeatureMockConstants {
    companion object {
        val album = Album(
            id = 1,
            title= "accusamus beatae ad facilis cum similique qui sunt",
        url=  "https://placehold.co/600x600/92c952/white/png",
        thumbnailUrl = "https://placehold.co/150x150/92c952/white/png",
        )

        val albumListItemUI = AlbumListItemUI(
            title = "accusamus beatae ad facilis cum similique qui sunt",
            imageUrl = "https://placehold.co/150x150/92c952/white/png",
        )
    }
}