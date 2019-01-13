package io.redgreen.benchpress.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

abstract class AbstractBenchPressApp : Application() {
  interface Initializer {
    fun initialize()
  }

  protected abstract val initializers: List<Initializer>

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
}
