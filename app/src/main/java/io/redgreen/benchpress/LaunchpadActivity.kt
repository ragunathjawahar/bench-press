package io.redgreen.benchpress

import android.os.Bundle
import io.redgreen.benchpress.architecture.BaseActivity

class LaunchpadActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.launchpad_activity)
  }
}
