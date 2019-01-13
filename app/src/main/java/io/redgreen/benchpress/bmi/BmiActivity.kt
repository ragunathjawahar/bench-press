package io.redgreen.benchpress.bmi

import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.bmi_activity.*

class BmiActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.bmi_activity)
    deleteMe()
  }

  private fun deleteMe() {
    bmiTextView.text = 24.toString()
    bmiCategoryTextView.text = getString(R.string.normal)
    weightTextView.text = getString(R.string.template_weight_si, 0.0)
    heightTextView.text = getString(R.string.template_height_si, 0.0)
  }
}
