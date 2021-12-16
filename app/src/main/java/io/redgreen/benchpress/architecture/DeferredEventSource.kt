package io.redgreen.benchpress.architecture

import com.spotify.mobius.EventSource
import com.spotify.mobius.disposables.Disposable
import com.spotify.mobius.functions.Consumer
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class DeferredEventSource<E> : EventSource<E> {
    private val events = LinkedBlockingQueue<E>()

    override fun subscribe(eventConsumer: Consumer<E>): Disposable {
        val run = AtomicBoolean(true)
        val thread = Thread {
            while (run.get()) {
                try {
                    val event = events.take()
                    if (run.get()) {
                        eventConsumer.accept(event)
                    }
                } catch (e: Throwable) {
                    // TODO(rj) 8/Jan/19 - Log this exception.
                }
            }
        }
        thread.start()
        return Disposable {
            run.set(false)
            thread.interrupt()
        }
    }

    @Synchronized
    fun notifyEvent(e: E) {
        events.offer(e)
    }
}