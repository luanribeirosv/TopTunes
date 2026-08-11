package com.toptunes.presentation.navigation

import androidx.navigation3.runtime.NavKey
import com.toptunes.presentation.model.AlbumUi
import kotlinx.serialization.Serializable

sealed interface AlbumRoute : NavKey {

    @Serializable
    data object AlbumListRoute : AlbumRoute

    @Serializable
    data class AlbumDetailRoute(val albumUi: AlbumUi) : AlbumRoute
}