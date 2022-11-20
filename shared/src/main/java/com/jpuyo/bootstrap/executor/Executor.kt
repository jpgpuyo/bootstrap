package com.jpuyo.bootstrap.executor

import kotlinx.coroutines.CoroutineDispatcher

interface Executor {
    val main: CoroutineDispatcher
    val bg: CoroutineDispatcher
}
