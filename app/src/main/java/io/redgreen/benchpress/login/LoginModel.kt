package io.redgreen.benchpress.login

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
  val email: String,
  val password: String,
  val attemptLoginAsyncOp: AsyncOp
) : Parcelable {
  companion object {
    val BLANK = LoginModel(
      email = "",
      password = "",
      attemptLoginAsyncOp = IDLE
    )
  }

  val isReadyForLogin: Boolean // TODO Proper domain modelling here.
    get() = (email.isNotBlank() && email.lastIndexOf('@') < email.lastIndexOf('.'))
        && password.length >= 8

  fun emailChanged(email: String): LoginModel =
    copy(email = email)

  fun passwordChanged(password: String): LoginModel =
    copy(password = password)

  fun loginAttempted(): LoginModel =
    copy(attemptLoginAsyncOp = IN_FLIGHT)

  fun authenticationSucceeded(): LoginModel =
    copy(attemptLoginAsyncOp = SUCCEEDED)

  fun authenticationFailed(): LoginModel =
    copy(attemptLoginAsyncOp = FAILED)
}
