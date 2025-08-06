package com.jperez.lbc.feature.composable

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jperez.lbc.feature.viewmodel.AlbumListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    val pagingItems = remember(state.value.items) {
        flow {
            emit(state.value.items)
        }
    }.collectAsLazyPagingItems()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Album List",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                )
                ConnectivityBanner()
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    all = 8.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
            ) {
                items(pagingItems.itemCount) { index ->
                    AlbumListItem(
                        album = pagingItems[index]!!,
                    )
                }
                pagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                LoadingView()
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                LoadingView()
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            item {
                                ErrorView(
                                    onRetry = { pagingItems.retry() }
                                )
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            item {
                                ErrorView(
                                    onRetry = { pagingItems.retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}