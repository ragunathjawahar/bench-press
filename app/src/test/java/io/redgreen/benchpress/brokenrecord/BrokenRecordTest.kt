package io.redgreen.benchpress.brokenrecord

import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

class BrokenRecordTest {
    private val cpuScheduler = TestScheduler()
    private val tenSecondsInMillis = Milliseconds.fromSeconds(10)

    @Test
    fun `it can fetch value as soon as there is a subscriber`() {
        // given
        val valueFromAsyncSource = "A"
        val brokenRecord = BrokenRecord
            .createSource<String>(tenSecondsInMillis, cpuScheduler) { Single.just(valueFromAsyncSource) }
        val testObserver = brokenRecord.poll().test()

        // when
        cpuScheduler.triggerActions()

        // then
        with(testObserver) {
            assertValueCount(1)
            assertValue(valueFromAsyncSource)
        }
    }

    @Test
    fun `it should poll every 'x' seconds after the initial fetch`() {
        // given
        val justA = "A"
        val brokenRecord = BrokenRecord
            .createSource<String>(tenSecondsInMillis, cpuScheduler) { Single.just(justA) }
        val testObserver = brokenRecord.poll().test()

        // when
        cpuScheduler.advanceTimeBy(19, SECONDS)

        // then
        with(testObserver) {
            assertValueCount(2)
            assertValues(justA, justA)
        }
    }
}
