package io.redgreen.benchpress.test

import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

class EffectHandlerTestCase<F, E>(
    effectHandler: ObservableTransformer<F, E>
) {
  private val incomingEffectsSubject = PublishSubject.create<F>()
  private val effectHandlerObservable = incomingEffectsSubject.compose(effectHandler)
  private val outgoingEffectsTestObserver = effectHandlerObservable.test()

  fun dispatchEffect(effect: F) {
    incomingEffectsSubject.onNext(effect)
  }

  fun assertOutgoingEvents(vararg events: E) {
    outgoingEffectsTestObserver.assertValues(*events)
  }

  fun assertNoOutgoingEvents() {
    outgoingEffectsTestObserver.assertNoValues()
  }
}
