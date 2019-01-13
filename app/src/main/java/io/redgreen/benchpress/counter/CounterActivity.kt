package io.redgreen.benchpress.counter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.counter_activity.*

class CounterActivity : BaseActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, CounterActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.counter_activity)
    deleteMe()
  }

  private fun deleteMe() {
    counterTextView.text = 0.toString()
  }
}
