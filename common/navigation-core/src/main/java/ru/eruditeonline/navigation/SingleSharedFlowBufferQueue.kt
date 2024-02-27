package ru.eruditeonline.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Буфер для значений SharedFlow.
 * Исправляет ситуацию с SharedFlow без буфера, когда у него еще нет подписчиков, и значение теряется,
 * так как tryEmit просто не шлет значение (с обычным emit работает и без этого)
 * По сути это кастомный буфер вместо буфера SharedFlow, потому что буфер SharedFlow будет возвращать новым подписичкам кеш
 * (Допил SharedFlow(0,0,SUSPEND) до SingleLiveEvent)
 *
 * TODO класс не готов к использованию, пока что есть баг, что onSubscription происходит раньше чем возможный tryEmit,
 *  поэтому буффер очищается еще когда он пустой
 *  Пока что следует использовать SharedFlow.emit
 */
class SingleSharedFlowBufferQueue<T> {

    private val buffer = LinkedBlockingQueue<T>()

    fun put(value: T) {
        buffer.put(value)
    }

    fun flush(
        sharedFlow: MutableSharedFlow<T>,
        sharingScope: CoroutineScope = CoroutineScope(EmptyCoroutineContext),
    ) {
        if (buffer.isEmpty()) return

        sharingScope.launch {
            while (buffer.isNotEmpty()) {
                buffer.poll()?.let { value ->
                    sharedFlow.tryEmit(value)
                }
            }
        }
    }
}

fun <T> MutableSharedFlow<T>.asSingleSharedFlow(buffer: SingleSharedFlowBufferQueue<T>): SharedFlow<T> {
    return asSharedFlow().onSubscription { buffer.flush(this@asSingleSharedFlow) }
}

fun <T> MutableSharedFlow<T>.tryEmitWithBuffer(buffer: SingleSharedFlowBufferQueue<T>, value: T) {
    if (!tryEmit(value)) {
        buffer.put(value)
    }
}