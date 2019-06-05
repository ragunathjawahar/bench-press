package io.redgreen.benchpress.brokenrecord

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

class BrokenRecord<S>(
    val source: () -> Single<S>
) {
    fun poll(): Observable<S> {
        return source().toObservable()
    }

    companion object {
        fun <S> createSource(
            millis: Milliseconds,
            cpuScheduler: Scheduler,
            source: () -> Single<S>
        ): BrokenRecord<S> {
            return BrokenRecord(source)
        }
    }
}
