package io.redgreen.benchpress.architecture.effecthandlers

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

class NoOpEffectHandler<E> : ObservableTransformer<Nothing, E> {
  override fun apply(upstream: Observable<Nothing>): ObservableSource<E> =
      Observable.empty<E>()
}
