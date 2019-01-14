package io.redgreen.benchpress.login

import android.os.Parcelable
import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*
import io.redgreen.benchpress.login.domain.Email
import io.redgreen.benchpress.login.domain.Password
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
  val email: Email,
  val password: Password,
  val attemptLoginAsyncOp: AsyncOp
) : Parcelable {
  companion object {
    val BLANK = LoginModel(
      email = Email(""),
      password = Password(""),
      attemptLoginAsyncOp = IDLE
    )
  }

  val isReadyForLogin: Boolean
    get() = email.isValid() && password.isValid()

  fun emailChanged(value: String): LoginModel =
    copy(email = Email(value))

  fun passwordChanged(value: String): LoginModel =
    copy(password = Password(value))

  fun loginAttempted(): LoginModel =
    copy(attemptLoginAsyncOp = IN_FLIGHT)

  fun authenticationSucceeded(): LoginModel =
    copy(attemptLoginAsyncOp = SUCCEEDED)

  fun authenticationFailed(): LoginModel =
    copy(attemptLoginAsyncOp = FAILED)
}
