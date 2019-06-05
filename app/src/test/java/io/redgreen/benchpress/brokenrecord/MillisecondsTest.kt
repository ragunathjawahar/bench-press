package io.redgreen.benchpress.brokenrecord

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MillisecondsTest {
    @Test
    fun `it can create Milliseconds object from seconds`() {
        assertThat(Milliseconds.fromSeconds(10).value)
            .isEqualTo(10_000)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `it cannot accept negative values`() {
        Milliseconds.fromSeconds(-1)
    }

    @Test
    fun `it can accept zero as a valid parameter`() {
      assertThat(Milliseconds.fromSeconds(0).value)
          .isEqualTo(0)
    }
}
