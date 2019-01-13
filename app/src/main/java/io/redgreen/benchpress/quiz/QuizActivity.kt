package io.redgreen.benchpress.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity

class QuizActivity : BaseActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, QuizActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.quiz_activity)
  }
}
