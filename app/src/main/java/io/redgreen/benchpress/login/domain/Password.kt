package io.redgreen.benchpress.login.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Password(
  val value: String
) : Parcelable {
  fun isValid(): Boolean =
    value.length >= 8
}
