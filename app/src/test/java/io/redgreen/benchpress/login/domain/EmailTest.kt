package io.redgreen.benchpress.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmailTest {
  @Test
  fun `empty email is invalid`() {
    assertThat(Email("").isValid())
      .isFalse()
  }

  @Test
  fun `valid email is valid`() {
    assertThat(Email("rick@nowhere.com").isValid())
      .isTrue()
  }

  // TODO(rj) 14/Jan/19 - Add missing tests.
}
