package io.redgreen.benchpress.login

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
import android.widget.Toast
import com.spotify.mobius.Update
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import io.redgreen.benchpress.architecture.android.listener.TextWatcherAdapter
import io.redgreen.benchpress.architecture.threading.DefaultSchedulersProvider
import io.redgreen.benchpress.login.effecthandlers.LoginEffectHandlers
import io.redgreen.benchpress.login.http.FakeLoginApi
import io.redgreen.benchpress.login.repo.FakeUserRepository
import io.redgreen.benchpress.login.view.LoginView
import io.redgreen.benchpress.login.view.LoginViewActions
import io.redgreen.benchpress.login.view.LoginViewRenderer
import kotlinx.android.synthetic.main.login_activity.*
import timber.log.Timber
import kotlin.LazyThreadSafetyMode.NONE

class LoginActivity :
    BaseActivity<LoginModel, LoginEvent, LoginEffect>(),
    LoginView,
    LoginViewActions {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, LoginActivity::class.java))
    }
  }

  private val viewRenderer by lazy(NONE) {
    LoginViewRenderer(this)
  }

  override fun layoutResId(): Int =
    R.layout.login_activity

  override fun setup() {
    setupViewEventListeners()
  }

  override fun initialModel(): LoginModel =
    LoginModel.BLANK

  override fun update(): Update<LoginModel, LoginEvent, LoginEffect> =
    LoginLogic

  override fun render(model: LoginModel) {
    Timber.d("%s -> %s", model::class.java.simpleName, model.toString())
    viewRenderer.render(model)
  }

  override fun effectHandler(): ObservableTransformer<LoginEffect, LoginEvent> =
    LoginEffectHandlers.create(FakeLoginApi(), FakeUserRepository(), this, DefaultSchedulersProvider())

  override fun enableLoginButton() {
    loginButton.isEnabled = true
  }

  override fun disableLoginButton() {
    loginButton.isEnabled = false
  }

  override fun showProgress() {
    authenticationProgressBar.visibility = View.VISIBLE
    window.setFlags(FLAG_NOT_TOUCHABLE, FLAG_NOT_TOUCHABLE)
    enableInteractiveViews(false)
  }

  override fun hideProgress() {
    authenticationProgressBar.visibility = View.INVISIBLE
    window.clearFlags(FLAG_NOT_TOUCHABLE)
    enableInteractiveViews(true)
  }

  override fun navigateToHome() {
    showToast("Login successful!")
  }

  override fun notifyAuthenticationFailed() {
    showToast("Oops… authentication failed.")
  }

  private fun setupViewEventListeners() {
    emailEditText.addTextChangedListener(object : TextWatcherAdapter() {
      override fun afterTextChanged(s: Editable) {
        eventSource.notifyEvent(EmailChangedEvent(s.toString()))
      }
    })

    passwordEditText.addTextChangedListener(object : TextWatcherAdapter() {
      override fun afterTextChanged(s: Editable) {
        eventSource.notifyEvent(PasswordChangedEvent(s.toString()))
      }
    })

    loginButton.setOnClickListener { eventSource.notifyEvent(LoginAttemptedEvent) }
  }

  private fun enableInteractiveViews(enabled: Boolean) {
    emailEditText.isEnabled = enabled
    passwordEditText.isEnabled = enabled
    loginButton.isEnabled = enabled
    forgotPasswordButton.isEnabled = enabled
  }

  private fun showToast(message: String) {
    Toast
      .makeText(this, message, Toast.LENGTH_SHORT)
      .show()
  }
}
