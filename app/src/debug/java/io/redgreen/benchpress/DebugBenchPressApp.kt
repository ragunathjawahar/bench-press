package io.redgreen.benchpress

import io.redgreen.benchpress.initializers.LeakCanaryInitializer
import io.redgreen.benchpress.initializers.LogEverythingTimberInitializer
import io.redgreen.benchpress.initializers.StrictModeInitializer

class DebugBenchPressApp : BenchPressApp() {
  override val initializers: List<Initializer>
    get() = listOf(
      LogEverythingTimberInitializer(),
      StrictModeInitializer(),
      LeakCanaryInitializer(this)
    )
}
