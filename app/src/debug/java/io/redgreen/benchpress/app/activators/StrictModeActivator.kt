package io.redgreen.benchpress.app.activators

import android.os.StrictMode
import io.redgreen.benchpress.app.AbstractBenchPressApp.Activator

class StrictModeActivator : Activator {
  override fun activate() {
    StrictMode.setThreadPolicy(
      StrictMode.ThreadPolicy
        .Builder()
        .detectAll()
        .penaltyLog()
        .penaltyDeath()
        .build()
    )
  }
}
