package com.toptunes.data.service

import com.toptunes.data.dto.response.TopAlbumsResponse
import com.toptunes.data.mapper.toDomain
import com.toptunes.data.networking.get
import com.toptunes.domain.model.Album
import com.toptunes.domain.service.AlbumService
import com.toptunes.domain.util.DataError
import com.toptunes.domain.util.Result
import com.toptunes.domain.util.map
import io.ktor.client.HttpClient

class KtorAlbumService(
    private val httpClient: HttpClient
) : AlbumService {

    override suspend fun getAlbums(): Result<List<Album>, DataError.Remote> {
        return httpClient.get<TopAlbumsResponse>(
            route = "/topalbums/limit=100/json"
        ).map { response ->
            response.feed.entry.map { it.toDomain() }
        }
    }
}