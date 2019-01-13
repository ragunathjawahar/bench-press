package io.redgreen.benchpress.app.initializers

import io.redgreen.benchpress.app.AbstractBenchPressApp
import timber.log.Timber

class LogEverythingTimberInitializer : AbstractBenchPressApp.Initializer {
  override fun initialize() {
    Timber.plant(Timber.DebugTree())
  }
}
