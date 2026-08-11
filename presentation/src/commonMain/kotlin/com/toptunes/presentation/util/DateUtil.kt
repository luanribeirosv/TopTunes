package com.toptunes.presentation.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

object DateUtil {

    fun formatReleaseDate(instant: Instant) =
        instant.toLocalDateTime(TimeZone.currentSystemDefault()).format(
            format = LocalDateTime.Format {
                day()
                char('/')
                monthNumber()
                char('/')
                year()
            }
        )
}