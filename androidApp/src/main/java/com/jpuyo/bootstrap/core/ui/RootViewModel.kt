package com.jpuyo.bootstrap.core.ui

import androidx.lifecycle.ViewModel
import com.jpuyo.bootstrap.domain.model.DomainError
import com.jpuyo.bootstrap.domain.model.Either
import com.jpuyo.bootstrap.error.ErrorHandler
import com.jpuyo.bootstrap.executor.Executor
import kotlinx.coroutines.withContext

open class RootViewModel(private val executor: Executor, private val errorHandler: ErrorHandler) :
    ViewModel() {

    protected suspend fun <T> execute(f: suspend () -> Either<DomainError, T>): Either<DomainError, T> =
        withContext(executor.bg) { f() }

    protected fun getErrorMessage(domainError: DomainError): String {
        return errorHandler.convert(domainError)
    }
}