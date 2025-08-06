package com.jperez.lbc.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jperez.lbc.domain.usecase.GetAlbumsUseCase
import com.jperez.lbc.feature.model.ListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

/**
 * ViewModel for managing the list of albums.
 *
 * This ViewModel interacts with the [GetAlbumsUseCase] to fetch albums
 */

class AlbumListViewModel : ViewModel() {

    private val getAlbumsUseCase: GetAlbumsUseCase by inject(GetAlbumsUseCase::class.java)

    private val _uiState = MutableStateFlow(ListUIState())
    val uiState: StateFlow<ListUIState> = _uiState

    init {
        getAlbums()
    }

    /**
     * Fetches the list of albums from the use case and updates the UI state.
     */
    fun getAlbums() {
        viewModelScope.launch {
            getAlbumsUseCase.execute()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _uiState.value = uiState.value.copy(
                        items = pagingData,
                    )
                }
        }
    }
}