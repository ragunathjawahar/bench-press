package io.redgreen.benchpress.brokenrecord

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MillisecondsTest {
    @Test
    fun `it can create Milliseconds object from seconds`() {
        assertThat(Milliseconds.fromSeconds(10).value)
            .isEqualTo(10_000)
    }
}
