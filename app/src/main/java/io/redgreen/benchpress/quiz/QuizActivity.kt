package io.redgreen.benchpress.quiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.redgreen.benchpress.R

class QuizActivity : AppCompatActivity() {
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
