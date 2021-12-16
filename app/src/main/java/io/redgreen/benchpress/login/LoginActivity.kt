package io.redgreen.benchpress.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.redgreen.benchpress.R

class LoginActivity : AppCompatActivity() {
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
