package com.toptunes.data.log

import co.touchlab.kermit.Logger
import com.toptunes.domain.log.TopTunesLogger

object KermitLogger: TopTunesLogger {

    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun info(message: String) {
        Logger.i(message)
    }

    override fun warn(message: String) {
        Logger.w(message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message, throwable)
    }
}