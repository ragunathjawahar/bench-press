package io.redgreen.benchpress.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity

class LoginActivity : BaseActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, LoginActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.login_activity)
  }
}
