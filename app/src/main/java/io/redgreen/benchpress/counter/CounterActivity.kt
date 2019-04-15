package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import com.spotify.mobius.Update
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.counter.model.CounterEvent
import io.redgreen.benchpress.counter.model.CounterLogic
import io.redgreen.benchpress.counter.model.CounterModel
import io.redgreen.benchpress.counter.model.DecrementEvent
import io.redgreen.benchpress.counter.model.IncrementEvent
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : BaseActivity<CounterModel, CounterEvent, Nothing>() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, CounterActivity::class.java))
    }
  }

  override fun layoutResId(): Int =
    R.layout.counter_activity

  override fun setup() {
    incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementEvent) }
    decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementEvent) }
  }

  override fun initialModel(): CounterModel =
    CounterModel.ZERO

  override fun update(): Update<CounterModel, CounterEvent, Nothing> =
    CounterLogic

  override fun render(model: CounterModel) {
    counterTextView.text = model.counter.toString()
  }

  override fun effectHandler(): ObservableTransformer<Nothing, CounterEvent> =
    ObservableTransformer { Observable.never() }
}
