package io.redgreen.benchpress.launchpad

import android.content.Context

data class Example(
  val title: String,
  val starter: (Context) -> Unit
)
