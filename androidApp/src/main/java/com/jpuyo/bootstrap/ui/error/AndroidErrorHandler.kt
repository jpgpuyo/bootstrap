package com.jpuyo.bootstrap.ui.error

import android.content.Context
import com.jpuyo.bootstrap.domain.model.DomainError
import com.jpuyo.bootstrap.error.ErrorHandler
import com.jpuyo.bootstrap.R

class AndroidErrorHandler(private val context: Context) : ErrorHandler {
    override fun convert(error: DomainError): String =
        when (error) {
            DomainError.InvalidCredentials -> context.getString(R.string.invalid_credentials)
            DomainError.NoInternet -> context.getString(R.string.nointernet_error)
            DomainError.NotFound -> context.getString(R.string.http_not_found)
            is DomainError.HttpStatusCodeNotHandled -> context.getString(R.string.http_status_code_not_handled)
            is DomainError.HttpErrorNotHandled -> context.getString(R.string.http_error_not_handled)
        }
}
