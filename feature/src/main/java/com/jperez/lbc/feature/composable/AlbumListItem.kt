package com.jperez.lbc.feature.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.jperez.lbc.domain.model.Album
import com.jperez.lbc.feature.mapper.AlbumListItemUIMapper
import com.jperez.lbc.feature.model.AlbumListItemUI
import com.jperez.lbc.feature.theme.LbcTheme
import org.koin.compose.koinInject

@Composable
fun AlbumListItem(
    album: Album,
    modifier: Modifier = Modifier,
    albumListItemUIMapper: AlbumListItemUIMapper = koinInject<AlbumListItemUIMapper>()
) {
    val itemUI: AlbumListItemUI = albumListItemUIMapper.mapTo(album)
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(120.dp),
                model = itemUI.imageUrl,
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .shimmerBackground()
                    )
                },
                contentDescription = "album image",
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 0.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = itemUI.title, style = typography.titleLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlbumListItemPreview() {
    LbcTheme {
        Column {
            AlbumListItem(
                album = PreviewConstant.album,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}