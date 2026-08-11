package com.toptunes.data.dto.response

import com.toptunes.data.dto.FeedDTO
import kotlinx.serialization.Serializable

@Serializable
data class TopAlbumsResponse(
    val feed: FeedDTO
)
