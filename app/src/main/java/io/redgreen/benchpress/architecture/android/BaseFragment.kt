package io.redgreen.benchpress.architecture.android

import android.support.v4.app.Fragment
import com.squareup.leakcanary.LeakCanary

abstract class BaseFragment : Fragment() {
  override fun onDestroy() {
    super.onDestroy()
    LeakCanary.installedRefWatcher().watch(this)
  }
}
