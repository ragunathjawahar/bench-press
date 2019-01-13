package io.redgreen.benchpress.initializers

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.redgreen.benchpress.BenchPressApp

class LeakCanaryInitializer(
  private val application: Application
) : BenchPressApp.Initializer {
  override fun initialize() {
    LeakCanary.install(application)
  }
}
