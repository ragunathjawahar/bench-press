package io.redgreen.benchpress.brokenrecord

import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test

class BrokenRecordTest {
    @Test
    fun `it can fetch value as soon as there is a subscriber`() {
        // given
        val pollingInterval = Milliseconds.fromSeconds(10)
        val cpuScheduler = TestScheduler()
        val valueFromAsyncSource = "A"
        val pollEvery10SecondsBrokenRecord = BrokenRecord
            .createSource<String>(pollingInterval, cpuScheduler) { Single.just(valueFromAsyncSource) }
        val testObserver = pollEvery10SecondsBrokenRecord.poll().test()

        // when
        cpuScheduler.triggerActions()

        // then
        with(testObserver) {
            assertValueCount(1)
            assertValue(valueFromAsyncSource)
        }
    }
}
