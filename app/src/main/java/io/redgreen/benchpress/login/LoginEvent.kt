package io.redgreen.benchpress.login

sealed class LoginEvent

data class EmailChangedEvent(
  val value: String
) : LoginEvent()

data class PasswordChangedEvent(
  val value: String
) : LoginEvent()

data class LoginAttemptedEvent(
  val email: String,
  val password: String
) : LoginEvent()

data class AuthenticationSucceededEvent(
  val authToken: String
) : LoginEvent()

object AuthenticationFailedEvent : LoginEvent()
