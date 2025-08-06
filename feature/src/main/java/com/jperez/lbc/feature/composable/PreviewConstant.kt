package com.jperez.lbc.feature.composable

import com.jperez.lbc.domain.model.Album

class PreviewConstant {
    companion object {
        val album = Album(
            title = "Mr",
            id = 1,
            url = "https://example.com/image.jpg",
            thumbnailUrl = "https://example.com/thumbnail.jpg",
        )
    }
}