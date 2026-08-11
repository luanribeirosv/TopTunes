package com.toptunes.data.service

import com.toptunes.data.networking.HttpClientFactory
import com.toptunes.domain.log.TopTunesLogger
import com.toptunes.domain.util.DataError
import com.toptunes.domain.util.Result
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

class KtorAlbumServiceTest {

    private class NoOpLogger : TopTunesLogger {
        override fun debug(message: String) {}
        override fun info(message: String) {}
        override fun warn(message: String) {}
        override fun error(message: String, throwable: Throwable?) {}
    }

    private val validFeedJson = """
        {
          "feed": {
            "entry": [
              {
                "id": {"label": "id-label", "attributes": {"im:id": "111"}},
                "im:name": {"label": "Album One"},
                "im:artist": {"label": "Artist One"},
                "im:image": [
                  {"label": "https://example.com/170.jpg", "attributes": {"height": "170"}}
                ],
                "im:releaseDate": {"label": "2024-05-10T07:00:00-07:00"},
                "category": {"attributes": {"label": "Pop"}},
                "im:price": {"label": "$9.99"}
              }
            ]
          }
        }
    """.trimIndent()

    private fun service(
        respondWith: (HttpRequestData) -> Pair<String, HttpStatusCode> = { validFeedJson to HttpStatusCode.OK }
    ): KtorAlbumService {
        val engine = MockEngine { request ->
            val (body, status) = respondWith(request)
            respond(
                content = body,
                status = status,
                headers = headersOf(HttpHeaders.ContentType, ContentType.Text.JavaScript.toString())
            )
        }
        val httpClient = HttpClientFactory(NoOpLogger()).create(engine)
        return KtorAlbumService(httpClient)
    }

    @Test
    fun success() = runTest {
        val albumService = service()

        val result = albumService.getAlbums()

        assertIs<Result.Success<*>>(result)
        val albums = (result as Result.Success).data
        assertEquals(1, albums.size)
        assertEquals("111", albums.first().id)
        assertEquals("Album One", albums.first().name)
        assertEquals("Artist One", albums.first().artist)
    }

    @Test
    fun serverError() = runTest {
        val albumService = service(respondWith = { "" to HttpStatusCode.InternalServerError })

        val result = albumService.getAlbums()

        assertEquals(Result.Failure(DataError.Remote.SERVER_ERROR), result)
    }

    @Test
    fun emptyFeed() = runTest {
        val emptyFeedJson = """{"feed": {"entry": []}}"""
        val albumService = service(respondWith = { emptyFeedJson to HttpStatusCode.OK })

        val result = albumService.getAlbums()

        assertIs<Result.Success<*>>(result)
        assertTrue((result as Result.Success).data.isEmpty())
    }
}
