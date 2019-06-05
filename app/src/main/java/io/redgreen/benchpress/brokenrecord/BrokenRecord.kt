package io.redgreen.benchpress.brokenrecord

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class BrokenRecord<S>(
    private val initialDelay: Milliseconds,
    private val pollingInterval: Milliseconds,
    private val cpuScheduler: Scheduler,
    private val source: () -> Single<S>
) {
    fun poll(): Observable<S> {
        return Observable
            .interval(initialDelay.value, pollingInterval.value, TimeUnit.MILLISECONDS, cpuScheduler)
            .flatMapSingle { source() }
    }

    companion object {
        fun <S> createSource(
            pollingInterval: Milliseconds,
            cpuScheduler: Scheduler, // TODO Protect developers from passing in an incorrect scheduler
            source: () -> Single<S>
        ): BrokenRecord<S> {
            return createSource(Milliseconds.ZERO, pollingInterval, cpuScheduler, source)
        }

        fun <S> createSource(
            initialDelay: Milliseconds,
            pollingInterval: Milliseconds,
            cpuScheduler: Scheduler, // TODO Protect developers from passing in an incorrect scheduler
            source: () -> Single<S>
        ): BrokenRecord<S> {
            return BrokenRecord(initialDelay, pollingInterval, cpuScheduler, source)
        }
    }
}
