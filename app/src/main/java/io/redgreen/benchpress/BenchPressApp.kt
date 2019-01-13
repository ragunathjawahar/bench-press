package io.redgreen.benchpress

import io.redgreen.benchpress.app.AbstractBenchPressApp

class BenchPressApp : AbstractBenchPressApp() {
  override val initializers: List<Initializer>
    get() = emptyList()
}
