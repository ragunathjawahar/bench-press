package io.redgreen.benchpress.counter


import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

class CounterEffectHandler<E> : ObservableTransformer<CounterEffect, E> {

    fun createEffectHandler(
        interact: Interactor
    ) :ObservableTransformer<CounterEffect, CounterEvent> {
        
        return RxMobius.subTypeEffectHandler<CounterEffect, CounterEvent>()
            .addConsumer(
                ShowErrorEffect::class.java,
                interact.showError(),
                AndroidSchedulers.mainThread()
            ).build()
        
    }
        

}

