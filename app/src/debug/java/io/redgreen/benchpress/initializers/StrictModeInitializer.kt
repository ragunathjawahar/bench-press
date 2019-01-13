package io.redgreen.benchpress.initializers

import android.os.StrictMode
import io.redgreen.benchpress.BenchPressApplication

class StrictModeInitializer : BenchPressApplication.Initializer {
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
