package io.redgreen.benchpress.bmi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.redgreen.benchpress.R
import kotlinx.android.synthetic.main.bmi_activity.*

class BmiActivity : AppCompatActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, BmiActivity::class.java))
    }
  }

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
