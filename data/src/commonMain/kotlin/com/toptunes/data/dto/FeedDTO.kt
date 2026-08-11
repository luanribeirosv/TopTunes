package com.toptunes.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FeedDTO(
    val entry: List<AlbumDTO>
)
