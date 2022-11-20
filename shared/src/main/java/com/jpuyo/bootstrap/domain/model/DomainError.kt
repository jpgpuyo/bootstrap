package com.jpuyo.bootstrap.domain.model

sealed class DomainError {
    object NoInternet : DomainError()
    object NotFound : DomainError()
    object InvalidCredentials : DomainError()
    data class HttpStatusCodeNotHandled(val message: String) : DomainError()
    data class HttpErrorNotHandled(val message: String) : DomainError()
}

object Success
