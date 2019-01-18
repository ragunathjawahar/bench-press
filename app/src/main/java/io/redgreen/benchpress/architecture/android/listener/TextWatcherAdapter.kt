package io.redgreen.benchpress.architecture.android.listener

import android.text.Editable
import android.text.TextWatcher

open class TextWatcherAdapter : TextWatcher {
  override fun afterTextChanged(s: Editable) {
    /* o-op */
  }

  override fun beforeTextChanged(
    s: CharSequence,
    start: Int,
    count: Int,
    after: Int
  ) {
    /* o-op */
  }

  override fun onTextChanged(
    s: CharSequence,
    start: Int,
    before: Int,
    count: Int
  ) {
    /* o-op */
  }
}
