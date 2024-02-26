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
 * TODO возможно дело не в этом, пока что не используется
 * Исправляет ситуацию с SharedFlow без буфера, когда у него еще нет подписчиков, и значение теряется
 * (Допил SharedFlow(0,0,SUSPED) до SingleLiveEvent)
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
    if (subscriptionCount.value > 0) {
        tryEmit(value)
    } else {
        buffer.put(value)
    }
}