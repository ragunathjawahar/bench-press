package io.redgreen.benchpress.counter.model

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object CounterLogic : Update<CounterModel, CounterEvent, Nothing> {
  override fun update(
    model: CounterModel,
    event: CounterEvent
  ): Next<CounterModel, Nothing> {
    val updatedModel = if (event is IncrementEvent) {
      model.increment()
    } else {
      model.decrement()
    }
    return next(updatedModel)
  }
}
