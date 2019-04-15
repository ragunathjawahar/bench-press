package io.redgreen.benchpress.login.effecthandlers

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.threading.SchedulersProvider
import io.redgreen.benchpress.login.AuthenticateUserEffect
import io.redgreen.benchpress.login.AuthenticationFailedEvent
import io.redgreen.benchpress.login.AuthenticationSucceededEvent
import io.redgreen.benchpress.login.GoToHomeEffect
import io.redgreen.benchpress.login.LoginEffect
import io.redgreen.benchpress.login.LoginEvent
import io.redgreen.benchpress.login.NotifyAuthenticationFailedEffect
import io.redgreen.benchpress.login.SaveTokenEffect
import io.redgreen.benchpress.login.http.LoginApi
import io.redgreen.benchpress.login.http.LoginRequest
import io.redgreen.benchpress.login.repo.UserRepository
import io.redgreen.benchpress.login.view.LoginViewActions

object LoginEffectHandlers {
  fun create(
    loginApi: LoginApi,
    userRepository: UserRepository,
    viewActions: LoginViewActions,
    schedulersProvider: SchedulersProvider
  ): ObservableTransformer<LoginEffect, LoginEvent> {
    return RxMobius
      .subtypeEffectHandler<LoginEffect, LoginEvent>()
      .addTransformer(AuthenticateUserEffect::class.java, authenticateUser(loginApi))
      .addConsumer(SaveTokenEffect::class.java) { userRepository.saveAuthToken(it.authToken) }
      .addAction(GoToHomeEffect::class.java, viewActions::navigateToHome, schedulersProvider.ui)
      .addAction(NotifyAuthenticationFailedEffect::class.java, viewActions::notifyAuthenticationFailed, schedulersProvider.ui)
      .build()
  }

  private fun authenticateUser(
    loginApi: LoginApi
  ): ObservableTransformer<AuthenticateUserEffect, LoginEvent> {
    return ObservableTransformer { authenticateUserEffects ->
      authenticateUserEffects
        .flatMapSingle {
          loginApi
            .login(LoginRequest(it.email, it.password))
            .map<LoginEvent> { response -> AuthenticationSucceededEvent(response.authToken) }
            .onErrorReturn { AuthenticationFailedEvent }
        }
    }
  }
}
