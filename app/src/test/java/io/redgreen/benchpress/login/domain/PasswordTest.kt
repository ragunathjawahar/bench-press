package io.redgreen.benchpress.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PasswordTest {
  @Test
  fun `empty password is invalid`() {
    assertThat(Password("").isValid())
      .isFalse()
  }

  @Test
  fun `password with less than 8 characters is invalid`() {
    assertThat(Password("1234567").isValid())
      .isFalse()
  }

  @Test
  fun `password with 8 characters is valid`() {
    assertThat(Password("12345678").isValid())
      .isTrue()
  }

  @Test
  fun `password with more than 8 characters is valid`() {
    assertThat(Password("1234567890").isValid())
      .isTrue()
  }
}
