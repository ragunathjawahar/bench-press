package io.redgreen.benchpress.login

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.getOrElse
import io.redgreen.benchpress.architecture.AsyncOp
import io.redgreen.benchpress.architecture.AsyncOp.*

data class LoginModel(
  val email: Option<String>,
  val password: Option<String>,
  val attemptLoginAsyncOp: AsyncOp
) {
  companion object {
    val BLANK = LoginModel(
      email = None,
      password = None,
      attemptLoginAsyncOp = IDLE
    )
  }

  val isReadyForLogin: Boolean // TODO Proper domain modelling here.
    get() = email
      .map { it.isNotBlank() && it.lastIndexOf('@') < it.lastIndexOf('.') }
      .and(password.map { it.length >= 8 })
      .getOrElse { false }

  fun emailChanged(email: String): LoginModel =
    copy(email = Some(email))

  fun passwordChanged(password: String): LoginModel =
    copy(password = Some(password))

  fun loginAttempted(): LoginModel =
    copy(attemptLoginAsyncOp = IN_FLIGHT)

  fun authenticationSucceeded(): LoginModel =
    copy(attemptLoginAsyncOp = SUCCEEDED)

  fun authenticationFailed(): LoginModel =
    copy(attemptLoginAsyncOp = FAILED)
}
