package io.redgreen.benchpress

import io.redgreen.benchpress.initializers.LeakCanaryInitializer
import io.redgreen.benchpress.initializers.StrictModeInitializer

class DebugBenchPressApplication : BenchPressApplication() {
  override val initializers: List<Initializer>
    get() = listOf(
      StrictModeInitializer(),
      LeakCanaryInitializer(this)
    )
}
