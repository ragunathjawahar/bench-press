package io.redgreen.benchpress.counter

import com.spotify.mobius.Effects.effects
import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update

object CounterLogic : Update<ModelCounter, CounterEvent, CounterEffect> {
    override fun update(
        model: ModelCounter,
        event: CounterEvent
    ): Next<ModelCounter, CounterEffect> {
        return if (event is IncrementCounterEvent) {
            next(model.increment())
        } else if (event is DecrementCounterEvent) {

            next(model.decrement())
        } else {
            noChange()
        }
    }
}
