package com.toptunes.presentation.album_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptunes.domain.service.AlbumService
import com.toptunes.domain.util.onFailure
import com.toptunes.domain.util.onSuccess
import com.toptunes.presentation.mapper.toUi
import com.toptunes.presentation.util.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumListViewModel(
    private val albumService: AlbumService
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val eventChannel = Channel<AlbumListEvent>()
    val events = eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(AlbumListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                loadAlbums()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AlbumListState()
        )

    fun onAction(action: AlbumListAction) {}

    fun loadAlbums() {
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            albumService.getAlbums()
                .onSuccess { response ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            albums = response.map { album -> album.toUi() }
                        )
                    }
                }
                .onFailure { error ->
                    eventChannel.send(AlbumListEvent.OnError(error.toUiText()))
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }
}