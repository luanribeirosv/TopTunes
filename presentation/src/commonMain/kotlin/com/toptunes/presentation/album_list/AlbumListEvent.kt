package com.toptunes.presentation.album_list

import com.toptunes.presentation.util.UiText

sealed interface AlbumListEvent {
    data class OnError(val error: UiText): AlbumListEvent
}