package io.redgreen.benchpress.login.repo

import timber.log.Timber

class FakeUserRepository : UserRepository {
  override fun saveAuthToken(authToken: String) {
    Timber.i("Pretending to save auth token: $authToken")
  }
}
