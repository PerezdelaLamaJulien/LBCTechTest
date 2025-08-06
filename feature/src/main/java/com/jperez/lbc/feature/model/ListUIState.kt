package com.jperez.lbc.feature.model

import androidx.paging.PagingData
import com.jperez.lbc.domain.model.Album

data class ListUIState(
    val items: PagingData<Album> = PagingData.empty(),
    )