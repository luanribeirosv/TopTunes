package com.toptunes.presentation.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Instant

class DateUtilTest {

    private fun instantAt(year: Int, month: Int, day: Int): Instant =
        LocalDateTime(year, month, day, 12, 0).toInstant(TimeZone.currentSystemDefault())

    @Test
    fun zeroPadsSingleDigits() {
        val instant = instantAt(2024, 1, 5)

        assertEquals("05/01/2024", DateUtil.formatReleaseDate(instant))
    }

    @Test
    fun keepsDoubleDigits() {
        val instant = instantAt(2024, 12, 25)

        assertEquals("25/12/2024", DateUtil.formatReleaseDate(instant))
    }
}
