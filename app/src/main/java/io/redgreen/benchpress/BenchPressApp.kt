package io.redgreen.benchpress

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import kotlin.LazyThreadSafetyMode.NONE

open class BenchPressApp : Application() {
  protected open val initializers: List<Initializer> by lazy(NONE) {
    emptyList<Initializer>()
  }

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    runInitializers()
  }

  private fun runInitializers() {
    initializers.forEach {
      it.initialize()
      Timber
        .tag(this::class.java.simpleName)
        .d("Run initializer: ${it::class.java.name}")
    }
  }

  interface Initializer {
    fun initialize()
  }
}
