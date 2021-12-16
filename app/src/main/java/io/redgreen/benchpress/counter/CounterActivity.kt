package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : BaseActivity<ModelCounter, CounterEvent, CounterEffect>(), Interactor {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, CounterActivity::class.java))
    }
  }

  override fun layoutResId(): Int {
    return R.layout.counter_activity
  }

  override fun setup() {
    incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementCounterEvent) }
    decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementCounterEvent) }
  }

  override fun initialModel():ModelCounter {
    return ModelCounter.ZERO
  }

  override fun updateFunction(
    model: ModelCounter,
    event: CounterEvent
  ): Next<ModelCounter,CounterEffect> {
    return CounterLogic.update(model, event)
  }

  override fun render(model: ModelCounter) {
    counterTextView.text = model.counter.toString()
  }

  override fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> {
    return CounterEffectHandler().createEffectHandler(
      interactor = this@CounterActivity
    )
  }
  
  override fun showError() {
    //show toast
  }
}


interface Interactor {
  fun showError()
}
