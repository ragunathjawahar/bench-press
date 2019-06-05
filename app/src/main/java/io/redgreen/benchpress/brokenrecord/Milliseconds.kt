package io.redgreen.benchpress.brokenrecord

import java.util.concurrent.TimeUnit.SECONDS

data class Milliseconds(
    val value: Long
) {
    companion object {
        fun fromSeconds(seconds: Int): Milliseconds {
            return Milliseconds(SECONDS.toMillis(seconds.toLong()))
        }
    }
}
