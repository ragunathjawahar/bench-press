package io.redgreen.benchpress.counter.model

sealed class CounterEvent

object IncrementEvent : CounterEvent()

object DecrementEvent : CounterEvent()
