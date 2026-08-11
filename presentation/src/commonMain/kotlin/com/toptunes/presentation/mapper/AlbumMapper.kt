package com.toptunes.presentation.mapper

import com.toptunes.domain.model.Album
import com.toptunes.presentation.model.AlbumUi
import com.toptunes.presentation.util.DateUtil

fun Album.toUi(): AlbumUi {
    return AlbumUi(
        id = id,
        name = name,
        artist = artist,
        urlImage = images.maxBy { it.height }.url,
        formattedReleaseDate = DateUtil.formatReleaseDate(releaseDate),
        category = category,
        price = price
    )
}