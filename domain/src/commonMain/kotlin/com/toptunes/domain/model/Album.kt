package com.toptunes.domain.model

import kotlin.time.Instant

data class Album(
    val id: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImage>,
    val releaseDate: Instant,
    val category: String,
    val price: String
)