package io.redgreen.benchpress.architecture.android

import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spotify.mobius.Connectable
import com.spotify.mobius.Connection
import com.spotify.mobius.First
import com.spotify.mobius.Init
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.Update
import com.spotify.mobius.android.MobiusAndroid
import com.spotify.mobius.extras.Connectables
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.functions.Function
import com.spotify.mobius.rx2.RxMobius
import com.squareup.leakcanary.LeakCanary
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.mobius.DeferredEventSource

abstract class BaseFragment<M : Parcelable, E, F> : Fragment(), Connectable<M, E> {
  companion object {
    private const val KEY_MODEL = "fragment_model"
  }

  private lateinit var controller: MobiusLoop.Controller<M, E>

  protected val eventSource by lazy(LazyThreadSafetyMode.NONE) {
    DeferredEventSource<E>()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    controller = createController(effectHandler(), resolveDefaultModel(savedInstanceState))
    controller.connect(Connectables.contramap(identity(), this))
    setup()
    return inflater.inflate(layoutResId(), container, false)
  }

  override fun onResume() {
    super.onResume()
    controller.start()
  }

  override fun onPause() {
    controller.stop()
    super.onPause()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelable(KEY_MODEL, controller.model)
  }

  override fun onDestroyView() {
    controller.disconnect()
    LeakCanary.installedRefWatcher().watch(this)
    super.onDestroyView()
  }

  override fun connect(output: Consumer<E>): Connection<M> {
    return object : Connection<M> {
      override fun accept(value: M) {
        render(value)
      }

      override fun dispose() {
        /* no-op, nothing to dispose */ // TODO(rj) dispose required?
      }
    }
  }

  @LayoutRes
  abstract fun layoutResId(): Int

  abstract fun initialModel(): M

  protected open fun init(): Init<M, F> =
    Init { model -> First.first(model) }

  abstract fun update(): Update<M, E, F>

  abstract fun effectHandler(): ObservableTransformer<F, E>

  abstract fun setup()

  abstract fun render(model: M)

  private fun createController(
    effectHandler: ObservableTransformer<F, E>,
    model: M
  ): MobiusLoop.Controller<M, E> {
    return MobiusAndroid
      .controller(createLoop(effectHandler), model)
  }

  private fun resolveDefaultModel(savedInstanceState: Bundle?): M =
    savedInstanceState?.getParcelable(KEY_MODEL) ?: initialModel()

  private fun identity(): Function<M, M> =
    Function { it }

  private fun createLoop(
    effectHandler: ObservableTransformer<F, E>
  ): MobiusLoop.Builder<M, E, F> {
    return RxMobius
      .loop(update(), effectHandler)
      .init(init())
      .eventSource(eventSource)
  }
}
