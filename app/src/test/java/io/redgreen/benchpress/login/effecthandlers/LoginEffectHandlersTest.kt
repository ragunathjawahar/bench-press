package io.redgreen.benchpress.login.effecthandlers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.redgreen.benchpress.login.*
import io.redgreen.benchpress.login.http.LoginApi
import io.redgreen.benchpress.login.http.LoginRequest
import io.redgreen.benchpress.login.http.LoginResponse
import io.redgreen.benchpress.login.repo.UserRepository
import io.redgreen.benchpress.login.view.LoginViewActions
import io.redgreen.benchpress.test.EffectHandlerTestCase
import org.junit.Test

class LoginEffectHandlersTest {
  private val loginApi = mock<LoginApi>()
  private val userRepository = mock<UserRepository>()
  private val viewActions = mock<LoginViewActions>()

  private val effectHandlerTestCase = EffectHandlerTestCase(
    LoginEffectHandlers.create(loginApi, userRepository, viewActions)
  )

  private val email = "someone@somewhere.in"
  private val password = "why-so-sneaky"

  @Test
  fun `when authenticate user succeeds, then dispatch authentication succeeded event`() {
    // given
    val authToken = "auth-token"
    whenever(loginApi.login(LoginRequest(email, password)))
      .thenReturn(Single.just(LoginResponse(authToken)))

    // when
    effectHandlerTestCase
      .dispatchEffect(AuthenticateUserEffect(email, password))

    // then
    effectHandlerTestCase
      .assertOutgoingEvents(AuthenticationSucceededEvent(authToken))
  }

  @Test
  fun `when authenticate user fails, then dispatch authentication failed event`() {
    // given
    whenever(loginApi.login(LoginRequest(email, password)))
      .thenReturn(Single.error(RuntimeException("Something blew up!")))

    // when
    effectHandlerTestCase
      .dispatchEffect(AuthenticateUserEffect(email, password))

    // then
    effectHandlerTestCase
      .assertOutgoingEvents(AuthenticationFailedEvent)
  }

  @Test
  fun `save token to user repository`() {
    // when
    val authToken = "auth-token"
    effectHandlerTestCase
      .dispatchEffect(SaveTokenEffect(authToken))

    // then
    effectHandlerTestCase
      .assertNoOutgoingEvents()

    verify(userRepository).saveAuthToken(authToken)
    verifyNoMoreInteractions(userRepository)
  }

  @Test
  fun `take user to the home screen`() {
    // when
    effectHandlerTestCase
      .dispatchEffect(GoToHomeEffect)

    // then
    effectHandlerTestCase
      .assertNoOutgoingEvents()

    verify(viewActions).navigateToHome()
    verifyNoMoreInteractions(viewActions)
  }

  @Test
  fun `notify user if authentication fails`() {
    effectHandlerTestCase
      .dispatchEffect(NotifyAuthenticationFailedEffect)

    // then
    effectHandlerTestCase
      .assertNoOutgoingEvents()

    verify(viewActions).notifyAuthenticationFailed()
    verifyNoMoreInteractions(viewActions)
  }
}
