package com.jpuyo.bootstrap.error

import com.jpuyo.bootstrap.domain.model.DomainError

interface ErrorHandler {
    fun convert(error: DomainError): String
}
