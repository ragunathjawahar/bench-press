package io.redgreen.benchpress.brokenrecord

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class BrokenRecord<S>(
    private val millis: Milliseconds,
    private val cpuScheduler: Scheduler,
    private val source: () -> Single<S>
) {
    fun poll(): Observable<S> {
        return Observable
            .interval(0, millis.value, TimeUnit.MILLISECONDS, cpuScheduler)
            .flatMapSingle { source() }
    }

    companion object {
        fun <S> createSource(
            millis: Milliseconds,
            cpuScheduler: Scheduler, // TODO Protect developers from passing in an incorrect scheduler
            source: () -> Single<S>
        ): BrokenRecord<S> {
            return BrokenRecord(millis, cpuScheduler, source)
        }
    }
}
