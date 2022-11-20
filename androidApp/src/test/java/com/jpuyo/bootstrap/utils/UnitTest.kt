package com.jpuyo.bootstrap.utils

import android.content.Context
import com.jpuyo.bootstrap.domain.model.Either
import com.jpuyo.bootstrap.executor.AndroidExecutor
import com.jpuyo.bootstrap.ui.error.AndroidErrorHandler
import com.nhaarman.mockitokotlin2.mock
import kotlin.test.assertTrue
import kotlin.test.asserter

open class UnitTest {

    protected var context = mock<Context>()

    protected fun errorHandler() = AndroidErrorHandler(context)

    protected fun testExecutor() = AndroidExecutor(coroutineDispatcherProvider = TestDispatcherProvider())

    fun <L, R, T> assertRight(expected: T, actual: Either<L, R>, message: String? = null) {
        assertTrue { actual.isRight }
        if (actual is Either.Right) {
            asserter.assertEquals(message, expected, actual.success)
        }
    }

    fun <L, R, T> assertLeft(expected: T, actual: Either<L, R>, message: String? = null) {
        assertTrue { actual.isLeft }
        if (actual is Either.Left) {
            asserter.assertEquals(message, expected, actual.error)
        }
    }
}