package com.jpuyo.bootstrap.common.datasource.remote.engine

import com.jpuyo.bootstrap.features.users.data.remote.mock.UsersMock
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*

val httpMockEngine = MockEngine { request ->
    val encodedPath = request.url.encodedPath
    when {
        encodedPath == "/api/users" -> respond(
            content = ByteReadChannel(UsersMock.USER_LIST),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
        Regex("/api/users/\\d+").containsMatchIn(encodedPath) -> respond(
            content = ByteReadChannel(UsersMock.USER_DETAIL),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json")
        )
        else -> {
            respond(
                content = ByteReadChannel(""),
                status = HttpStatusCode.NotImplemented,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    }
}