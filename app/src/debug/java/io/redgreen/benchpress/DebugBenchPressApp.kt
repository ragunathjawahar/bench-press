package io.redgreen.benchpress

import io.redgreen.benchpress.app.AbstractBenchPressApp
import io.redgreen.benchpress.app.activators.LeakCanaryActivator
import io.redgreen.benchpress.app.activators.LogEverythingTimberActivator
import io.redgreen.benchpress.app.activators.StrictModeActivator

class DebugBenchPressApp : AbstractBenchPressApp() {
  override val activators: List<Activator>
    get() = listOf(
      LogEverythingTimberActivator(),
      LeakCanaryActivator(this),
      StrictModeActivator()
    )
}
