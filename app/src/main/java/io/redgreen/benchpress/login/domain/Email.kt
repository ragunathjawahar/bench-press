package io.redgreen.benchpress.login.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Email(
  val value: String
) : Parcelable {
  /* PS: This is a very naive validation logic. */
  fun isValid(): Boolean {
    return value.isNotBlank()
        && value.lastIndexOf('@') < value.lastIndexOf('.')
  }
}
