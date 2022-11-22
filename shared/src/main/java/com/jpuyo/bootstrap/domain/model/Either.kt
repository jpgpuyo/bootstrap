/**
 * Copyright (C) 2019 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modifications Copyright (C) 2021 Joaquim Puyo Garrido
 *
 * Simplified version of
 * https://github.com/android10/Android-CleanArchitecture-Kotlin/blob/main/app/src/main/kotlin/com/fernandocejas/sample/core/functional/Either.kt
 *
 */
package com.jpuyo.bootstrap.domain.model

sealed class Either<L, R> {

    val isRight get() = this is Right<L, R>
    val isLeft get() = this is Left<L, R>

    class Left<L, R>(val error: L) : Either<L, R>() {
        override fun toString(): String = "Left $error"
    }

    class Right<L, R>(val success: R) : Either<L, R>() {
        override fun toString(): String = "Right $success"
    }

    suspend infix fun <Rp> map(f: suspend (R) -> Rp): Either<L, Rp> {
        return when (this) {
            is Left -> Left(this.error)
            is Right -> Right(f(this.success))
        }
    }

    suspend infix fun <Rp> flatMap(f: suspend (Right<L, R>) -> Either<L, Rp>): Either<L, Rp> {
        return when (this) {
            is Left -> Left(error)
            is Right -> f(this)
        }
    }

    fun fold(error: (L) -> Unit, success: (R) -> Unit) {
        when (this) {
            is Left -> error(this.error)
            is Right -> success(this.success)
        }
    }

    fun <L, R> Either<L, R>.getOrElse(value: R): R =
        when (this) {
            is Left -> value
            is Right -> success
        }
}
