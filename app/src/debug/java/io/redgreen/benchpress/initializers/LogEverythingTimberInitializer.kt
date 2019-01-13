package io.redgreen.benchpress.initializers

import io.redgreen.benchpress.BenchPressApp
import timber.log.Timber

class LogEverythingTimberInitializer : BenchPressApp.Initializer {
  override fun initialize() {
    Timber.plant(Timber.DebugTree())
  }
}
