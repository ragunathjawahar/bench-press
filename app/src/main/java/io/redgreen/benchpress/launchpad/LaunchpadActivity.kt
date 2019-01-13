package io.redgreen.benchpress.launchpad

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.redgreen.benchpress.R
import io.redgreen.benchpress.bmi.BmiActivity
import io.redgreen.benchpress.counter.CounterActivity
import io.redgreen.benchpress.hellostranger.HelloStrangerActivity
import io.redgreen.benchpress.imagepicker.ImagePickerActivity
import io.redgreen.benchpress.login.LoginActivity
import io.redgreen.benchpress.quiz.QuizActivity
import kotlinx.android.synthetic.main.launchpad_activity.*
import kotlin.LazyThreadSafetyMode.NONE

class LaunchpadActivity : AppCompatActivity() {
  private val examples by lazy(NONE) {
    listOf(
      Example(getString(R.string.counter_title)) { context -> CounterActivity.start(context) },
      Example(getString(R.string.hello_stranger_title)) { context -> HelloStrangerActivity.start(context) },
      Example(getString(R.string.bmi_title)) { context -> BmiActivity.start(context) },
      Example(getString(R.string.login_title)) { context -> LoginActivity.start(context) },
      Example(getString(R.string.image_picker_title)) { context -> ImagePickerActivity.start(context) },
      Example(getString(R.string.quiz_title)) { context -> QuizActivity.start(context) }
    )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.launchpad_activity)
    setupExamplesRecyclerView()
  }

  private fun setupExamplesRecyclerView() {
    val verticalLinearLayoutManager = LinearLayoutManager(this)
    val itemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
    with(examplesRecyclerView) {
      layoutManager = verticalLinearLayoutManager
      adapter = ExamplesRecyclerViewAdapter(examples)
      addItemDecoration(itemDecoration)
    }
  }
}
