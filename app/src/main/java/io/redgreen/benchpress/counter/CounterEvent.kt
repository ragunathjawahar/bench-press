package io.redgreen.benchpress.counter

sealed class CounterEvent

object IncrementCounterEvent : CounterEvent()
object DecrementCounterEvent : CounterEvent()