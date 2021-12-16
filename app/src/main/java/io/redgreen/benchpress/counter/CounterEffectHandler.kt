package io.redgreen.benchpress.counter


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

class CounterEffectHandler<E> : ObservableTransformer<CounterEffect, E> {

    override fun apply(upstream: Observable<CounterEffect>): ObservableSource<E> =
        Observable.empty<E>()


}

