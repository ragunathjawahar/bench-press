package io.redgreen.benchpress.login.http

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class FakeLoginApi : LoginApi {
  override fun login(
    loginRequest: LoginRequest
  ): Single<LoginResponse> {
    return Single
      .just(LoginResponse("some-unreal-auth-token"))
      .delay(3000, TimeUnit.MILLISECONDS)
  }
}
