package io.redgreen.benchpress

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import kotlin.LazyThreadSafetyMode.NONE

open class BenchPressApplication : Application() {
  protected open val initializers: List<Initializer> by lazy(NONE) {
    emptyList<Initializer>()
  }

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    initializers.forEach(Initializer::initialize)
  }

  interface Initializer {
    fun initialize()
  }
}
