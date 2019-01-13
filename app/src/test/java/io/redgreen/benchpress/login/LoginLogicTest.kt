package io.redgreen.benchpress.login

import com.google.common.truth.Truth.assertThat
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class LoginLogicTest {
  private val updateSpec = UpdateSpec(LoginLogic::update)

  private val blankModel = LoginModel.BLANK

  private val validEmail = "leo@tmnt.org"
  private val validPassword = "april-autumn"
  private val validModel = blankModel
    .emailChanged(validEmail)
    .passwordChanged(validPassword)

  @Test
  fun `user can change email`() {
    val email = "james@mi5.co.uk"

    updateSpec
      .given(blankModel)
      .`when`(EmailChangedEvent(email))
      .then(
        assertThatNext(
          hasModel(blankModel.emailChanged(email)),
          hasNoEffects()
        )
      )
  }

  @Test
  fun `user can change password`() {
    val password = "a-secret-forever"

    updateSpec
      .given(blankModel)
      .`when`(PasswordChangedEvent(password))
      .then(
        assertThatNext(
          hasModel(blankModel.passwordChanged(password)),
          hasNoEffects()
        )
      )
  }

  @Test
  fun `disable login button when email and-or password are-is invalid`() {
    val invalidEmail = "not-an-email"
    val invalidPassword = "x"
    val invalidModel = blankModel
      .emailChanged(invalidEmail)
      .passwordChanged(invalidPassword)

    updateSpec
      .given(blankModel)
      .whenEvents(
        EmailChangedEvent(invalidEmail),
        PasswordChangedEvent(invalidPassword)
      )
      .then(
        assertThatNext(
          hasModel(invalidModel),
          hasNoEffects()
        )
      )

    assertThat(invalidModel.isReadyForLogin)
      .isFalse()
  }

  @Test
  fun `enable login button when both email and password are valid`() {
    updateSpec
      .given(blankModel)
      .whenEvents(
        EmailChangedEvent(validEmail),
        PasswordChangedEvent(validPassword)
      )
      .then(
        assertThatNext(
          hasModel(validModel),
          hasNoEffects()
        )
      )

    assertThat(validModel.isReadyForLogin)
      .isTrue()
  }

  @Test
  fun `user can attempt login with valid email and password`() {
    updateSpec
      .given(validModel)
      .whenEvent(LoginAttemptedEvent)
      .then(
        assertThatNext(
          hasModel(validModel.loginAttempted()),
          hasEffects(AuthenticateUserEffect(validEmail, validPassword) as LoginEffect)
        )
      )
  }

  @Test
  fun `when authentication is successful, then save the token and take user to the home screen`() {
    val loginAttemptedModel = validModel
      .loginAttempted()
    val authToken = "auth-token"

    updateSpec
      .given(loginAttemptedModel)
      .`when`(AuthenticationSucceededEvent(authToken))
      .then(
        assertThatNext(
          hasModel(loginAttemptedModel.authenticationSucceeded()),
          hasEffects(SaveTokenEffect(authToken), GoToHomeEffect)
        )
      )
  }

  @Test
  fun `when authentication fails, then notify user`() {
    val loginAttemptedModel = validModel
      .loginAttempted()

    updateSpec
      .given(loginAttemptedModel)
      .`when`(AuthenticationFailedEvent)
      .then(
        assertThatNext(
          hasModel(loginAttemptedModel.authenticationFailed()),
          hasEffects(NotifyAuthenticationFailedEffect as LoginEffect)
        )
      )
  }
}
