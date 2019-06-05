package io.redgreen.benchpress.brokenrecord

import java.util.concurrent.TimeUnit.SECONDS

data class Milliseconds(
    val value: Long
) {
    companion object {
        val ZERO = fromSeconds(0)

        fun fromSeconds(seconds: Int): Milliseconds {
            return Milliseconds(SECONDS.toMillis(seconds.toLong()))
        }
    }
}
