package io.redgreen.benchpress.login.repo

interface UserRepository {
  fun saveAuthToken(authToken: String)
}
