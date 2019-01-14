package io.redgreen.benchpress.login

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
  override fun update(
    model: LoginModel,
    event: LoginEvent
  ): Next<LoginModel, LoginEffect> {
    return when (event) {
      is EmailChangedEvent -> next(model.emailChanged(event.value))

      is PasswordChangedEvent -> next(model.passwordChanged(event.value))

      is LoginAttemptedEvent -> next(
        model.loginAttempted(),
        setOf(AuthenticateUserEffect(model.email.value, model.password.value))
      )

      is AuthenticationSucceededEvent -> next(
        model.authenticationSucceeded(),
        setOf(SaveTokenEffect(event.authToken), GoToHomeEffect)
      )

      is AuthenticationFailedEvent -> next(
        model.authenticationFailed(),
        setOf(NotifyAuthenticationFailedEffect)
      )
    }
  }
}
