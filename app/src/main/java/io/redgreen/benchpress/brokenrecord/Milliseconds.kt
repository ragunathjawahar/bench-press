package io.redgreen.benchpress.brokenrecord

import java.util.concurrent.TimeUnit.SECONDS

class Milliseconds private constructor(
    val value: Long
) {
    companion object {
        val ZERO = fromSeconds(0)

        fun fromSeconds(seconds: Int): Milliseconds {
            if (seconds < 0) throw IllegalArgumentException("`seconds` cannot be negative, it was $seconds.")
            return Milliseconds(SECONDS.toMillis(seconds.toLong()))
        }
    }
}
