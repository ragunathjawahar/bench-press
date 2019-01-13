package io.redgreen.benchpress.login.http

import io.reactivex.Single

interface LoginApi {
  fun login(loginRequest: LoginRequest): Single<LoginResponse>
}
