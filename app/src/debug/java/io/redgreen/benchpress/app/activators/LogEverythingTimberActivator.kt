package io.redgreen.benchpress.app.activators

import io.redgreen.benchpress.app.AbstractBenchPressApp.Activator
import timber.log.Timber

class LogEverythingTimberActivator : Activator {
  override fun activate() {
    Timber.plant(Timber.DebugTree())
  }
}
