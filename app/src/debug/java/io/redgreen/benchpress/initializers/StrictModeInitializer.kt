package io.redgreen.benchpress.initializers

import android.os.StrictMode
import io.redgreen.benchpress.BenchPressApp

class StrictModeInitializer : BenchPressApp.Initializer {
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
