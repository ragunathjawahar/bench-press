package io.redgreen.benchpress.login.view

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.redgreen.benchpress.login.LoginModel
import org.junit.Test

class LoginViewRendererTest {
  private val view = mock<LoginView>()
  private val viewRenderer = LoginViewRenderer(view)

  private val validEmail = "rj@mobsandgeeks.com"
  private val validPassword = "not-so-secret"
  private val validModel = LoginModel
    .BLANK
    .emailChanged(validEmail)
    .passwordChanged(validPassword)

  @Test
  fun `it can enable 'log in' button for valid models`() {
    // when
    viewRenderer.render(validModel)

    // then
    verify(view).enableLoginButton()
    verifyNoMoreInteractions(view)
  }

  @Test
  fun `it can disable 'log in' button for invalid models`() {
    // when
    viewRenderer.render(LoginModel.BLANK)

    // then
    verify(view).disableLoginButton()
    verifyNoMoreInteractions(view)
  }

  @Test
  fun `it can show the progress bar user is logging in`() {
    // given
    val loginAttemptedModel = validModel.loginAttempted()

    // when
    viewRenderer.render(loginAttemptedModel)

    // then
    verify(view).showProgress()
    verifyNoMoreInteractions(view)
  }

  @Test
  fun `it hides progress bar when login succeeds`() {
    // given
    val authenticationSucceededModel = validModel
      .loginAttempted()
      .authenticationSucceeded()

    // when
    viewRenderer.render(authenticationSucceededModel)

    // then
    verify(view).hideProgress()
    verifyNoMoreInteractions(view)
  }

  @Test
  fun `it hides progress bar when login fails`() {
    // given
    val authenticationFailedModel = validModel
      .loginAttempted()
      .authenticationFailed()

    // when
    viewRenderer.render(authenticationFailedModel)

    // then
    verify(view).hideProgress()
    verifyNoMoreInteractions(view)
  }
}
