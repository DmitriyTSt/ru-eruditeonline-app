package ru.eruditeonline.coroutine

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

fun DispatcherProvider(): DispatcherProvider {
    return DispatcherProviderImpl()
}