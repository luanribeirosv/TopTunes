package com.toptunes.domain.util

sealed interface DataError: Error {
    enum class Remote: DataError {
        BAD_REQUEST,
        REQUEST_TIMEOUT,
        NOT_FOUND,
        NO_INTERNET,
        SERVER_ERROR,
        SERVICE_UNAVAILABLE,
        SERIALIZATION,
        UNKNOWN
    }
}