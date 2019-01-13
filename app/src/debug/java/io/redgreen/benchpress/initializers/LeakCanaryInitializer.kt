package io.redgreen.benchpress.initializers

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.redgreen.benchpress.BenchPressApplication

class LeakCanaryInitializer(
  private val application: Application
) : BenchPressApplication.Initializer {
  override fun initialize() {
    LeakCanary.install(application)
  }
}
