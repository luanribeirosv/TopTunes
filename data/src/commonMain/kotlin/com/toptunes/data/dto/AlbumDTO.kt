package com.toptunes.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDTO(
    val id: ValueDTO.Id,
    @SerialName("im:name")
    val name: ValueDTO.Text,
    @SerialName("im:artist")
    val artist: ValueDTO.Text,
    @SerialName("im:image")
    val image: List<ValueDTO.Image>,
    @SerialName("im:releaseDate")
    val releaseDate: ValueDTO.Text,
    val category: ValueDTO.Category,
    @SerialName("im:price")
    val price: ValueDTO.Text
)