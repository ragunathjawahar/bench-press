package io.redgreen.benchpress

import io.redgreen.benchpress.app.AbstractBenchPressApp
import io.redgreen.benchpress.app.activators.NeverLogTimberActivator

class BenchPressApp : AbstractBenchPressApp() {
  override val activators: List<Activator>
    get() = listOf(
      NeverLogTimberActivator()
    )
}
