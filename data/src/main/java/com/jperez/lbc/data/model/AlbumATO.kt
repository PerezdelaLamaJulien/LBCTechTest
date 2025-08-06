package com.jperez.lbc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AlbumATO(
    val id: Int,
   val albumId: Int,
     val title: String,
   val url: String,
    val thumbnailUrl: String,
    )
