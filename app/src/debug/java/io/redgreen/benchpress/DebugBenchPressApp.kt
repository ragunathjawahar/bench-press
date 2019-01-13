package io.redgreen.benchpress

import io.redgreen.benchpress.app.AbstractBenchPressApp
import io.redgreen.benchpress.app.initializers.LeakCanaryInitializer
import io.redgreen.benchpress.app.initializers.LogEverythingTimberInitializer
import io.redgreen.benchpress.app.initializers.StrictModeInitializer

class DebugBenchPressApp : AbstractBenchPressApp() {
  override val initializers: List<Initializer>
    get() = listOf(
      LogEverythingTimberInitializer(),
      StrictModeInitializer(),
      LeakCanaryInitializer(this)
    )
}
