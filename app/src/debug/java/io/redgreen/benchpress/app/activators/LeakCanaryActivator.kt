package io.redgreen.benchpress.app.activators

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.redgreen.benchpress.app.AbstractBenchPressApp.Activator

class LeakCanaryActivator(
  private val application: Application
) : Activator {
  override fun activate() {
    LeakCanary.install(application)
  }
}
