package io.redgreen.benchpress.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import kotlin.LazyThreadSafetyMode.NONE
import kotlin.reflect.KClass

abstract class AbstractBenchPressApp : Application() {
  interface Activator {
    fun activate()
  }

  protected abstract val activators: List<Activator>

  private val tag by lazy(NONE) { this::class.java.simpleName }

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    runActivators()
  }

  private fun runActivators() {
    activators.forEach {
      it.activate()
      logActivation(it::class)
    }
  }

  private fun logActivation(activatorClass: KClass<out Activator>) {
    Timber
      .tag(tag)
      .d("Run activator: ${activatorClass.java.name}")
  }
}
