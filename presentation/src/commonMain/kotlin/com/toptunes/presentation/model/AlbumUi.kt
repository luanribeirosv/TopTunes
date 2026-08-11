package com.toptunes.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class AlbumUi(
    val id: String,
    val name: String,
    val artist: String,
    val urlImage: String,
    val formattedReleaseDate: String,
    val category: String,
    val price: String
)