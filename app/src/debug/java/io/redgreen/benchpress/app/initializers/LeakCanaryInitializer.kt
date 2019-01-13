package io.redgreen.benchpress.app.initializers

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.redgreen.benchpress.app.AbstractBenchPressApp

class LeakCanaryInitializer(
  private val application: Application
) : AbstractBenchPressApp.Initializer {
  override fun initialize() {
    LeakCanary.install(application)
  }
}
