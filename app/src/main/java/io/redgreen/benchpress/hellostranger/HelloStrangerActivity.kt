package io.redgreen.benchpress.hellostranger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity

class HelloStrangerActivity : BaseActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, HelloStrangerActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.hello_stranger_activity)
  }
}
