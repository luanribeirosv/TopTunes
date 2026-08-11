package com.toptunes.presentation.album_list

import com.toptunes.presentation.model.AlbumUi

data class AlbumListState(
    val isLoading: Boolean = false,
    val albums: List<AlbumUi> = emptyList(),
)
