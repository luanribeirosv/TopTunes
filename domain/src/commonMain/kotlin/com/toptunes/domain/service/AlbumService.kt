package com.toptunes.domain.service

import com.toptunes.domain.model.Album
import com.toptunes.domain.util.DataError
import com.toptunes.domain.util.Result

interface AlbumService {

    suspend fun getAlbums(): Result<List<Album>, DataError.Remote>

}