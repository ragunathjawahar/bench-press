package io.redgreen.benchpress.app.activators

import io.redgreen.benchpress.app.AbstractBenchPressApp.Activator
import timber.log.Timber

class NeverLogTimberActivator : Activator {
  override fun activate() {
    Timber.plant(NeverLogTree())
  }

  private class NeverLogTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
      /* dev/null for your logs */
    }
  }
}
