package com.jpuyo.bootstrap.common.datasource.remote

import com.jpuyo.bootstrap.common.datasource.remote.engine.httpMockEngine
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal fun <T> URLBuilder.params(vararg params: Map.Entry<String, T>) {
    parameters.apply {
        params.forEach {
            append(it.key, it.value.toString())
        }
    }
}

internal fun URLBuilder.withPath(path: String, block: URLBuilder.(URLBuilder) -> Unit = {}) {
    encodedPath = path
    block(this)
}

fun buildClient(
    endpoint: String,
    isDebug: Boolean,
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient =
    HttpClient {
        expectSuccess = true
        defaultRequest {
            val endpointUrlBuilder = URLBuilder(endpoint)
            url {
                protocol = endpointUrlBuilder.protocol
                host = endpointUrlBuilder.host
            }
        }
        if (isDebug) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
        install(ContentNegotiation) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
                prettyPrint = true
            }
            json(json)
        }
        block(this)
    }

fun buildMockClient(
    endpoint: String,
    isDebug: Boolean,
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient =
    HttpClient(engine = httpMockEngine) {
        expectSuccess = true
        defaultRequest {
            val endpointUrlBuilder = URLBuilder(endpoint)
            url {
                protocol = endpointUrlBuilder.protocol
                host = endpointUrlBuilder.host
            }
        }
        if (isDebug) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
        install(ContentNegotiation) {
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
                prettyPrint = true
            }
            json(json)
        }
        block(this)
    }