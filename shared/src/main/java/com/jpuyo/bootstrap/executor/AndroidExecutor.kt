package com.jpuyo.bootstrap.executor

import kotlinx.coroutines.CoroutineDispatcher

class AndroidExecutor(
    coroutineDispatcherProvider: CoroutineDispatcherProvider = AndroidDispatcherProvider()
): Executor {
    override val main: CoroutineDispatcher = coroutineDispatcherProvider.main
    override val bg: CoroutineDispatcher = coroutineDispatcherProvider.io
}
