package com.toptunes.presentation.album_list

import com.toptunes.presentation.model.AlbumUi

sealed interface AlbumListAction {
    data class OnSelectAlbum(val album: AlbumUi): AlbumListAction
}