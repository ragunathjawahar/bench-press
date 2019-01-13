package io.redgreen.benchpress.login

sealed class LoginEffect

data class AuthenticateUserEffect(
  val email: String,
  val password: String
) : LoginEffect()

data class SaveTokenEffect(
  val authToken: String
) : LoginEffect()

object GoToHomeEffect : LoginEffect()

object NotifyAuthenticationFailedEffect : LoginEffect()
