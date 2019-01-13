package io.redgreen.benchpress.login.view

import io.redgreen.benchpress.architecture.AsyncOp.*
import io.redgreen.benchpress.login.LoginModel

class LoginViewRenderer(
  val view: LoginView
) {
  fun render(model: LoginModel) {
    if (model.attemptLoginAsyncOp == IN_FLIGHT) {
      view.showProgress()
      return
    } else if (model.attemptLoginAsyncOp == SUCCEEDED || model.attemptLoginAsyncOp == FAILED) {
      view.hideProgress()
      return
    }

    if (model.isReadyForLogin) {
      view.enableLoginButton()
    } else {
      view.disableLoginButton()
    }
  }
}
