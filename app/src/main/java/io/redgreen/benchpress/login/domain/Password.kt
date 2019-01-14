package io.redgreen.benchpress.login.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Password(
  val value: String
) : Parcelable {
  fun isValid(): Boolean =
    value.length >= 8

  /* Overriding this function here because we want to redact sensitive information in logs. */
  override fun toString(): String {
    return "Password(value=███████████)"
  }
}
