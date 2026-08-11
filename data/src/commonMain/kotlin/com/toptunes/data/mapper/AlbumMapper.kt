package com.toptunes.data.mapper

import com.toptunes.data.dto.AlbumDTO
import com.toptunes.data.dto.ValueDTO
import com.toptunes.domain.model.Album
import com.toptunes.domain.model.AlbumImage
import kotlin.time.Instant

fun AlbumDTO.toDomain() = Album(
    id = id.attributes.id,
    name = name.label,
    artist = artist.label,
    images = image.map { it.toDomain() },
    releaseDate = Instant.parse(releaseDate.label),
    category = category.attributes.label,
    price = price.label
)

fun ValueDTO.Image.toDomain() = AlbumImage(
    url = label,
    height = attributes.height.toInt()
)