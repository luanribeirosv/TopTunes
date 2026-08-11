package com.toptunes.presentation.util

import com.toptunes.domain.util.DataError
import toptunes.presentation.generated.resources.Res
import toptunes.presentation.generated.resources.error_bad_request
import toptunes.presentation.generated.resources.error_no_internet
import toptunes.presentation.generated.resources.error_not_found
import toptunes.presentation.generated.resources.error_request_timeout
import toptunes.presentation.generated.resources.error_serialization
import toptunes.presentation.generated.resources.error_server
import toptunes.presentation.generated.resources.error_service_unavailable
import toptunes.presentation.generated.resources.error_unknown

fun DataError.toUiText(): UiText {
    val resource = when(this) {
        DataError.Remote.BAD_REQUEST -> Res.string.error_bad_request
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.NOT_FOUND -> Res.string.error_not_found
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER_ERROR -> Res.string.error_server
        DataError.Remote.SERVICE_UNAVAILABLE -> Res.string.error_service_unavailable
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }
    return UiText.Resource(resource)
}