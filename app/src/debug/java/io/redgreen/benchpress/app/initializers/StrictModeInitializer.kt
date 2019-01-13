package io.redgreen.benchpress.app.initializers

import android.os.StrictMode
import io.redgreen.benchpress.app.AbstractBenchPressApp

class StrictModeInitializer : AbstractBenchPressApp.Initializer {
  override fun initialize() {
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
