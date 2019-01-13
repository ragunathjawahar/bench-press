package io.redgreen.benchpress.imagepicker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity

class ImagePickerActivity : BaseActivity() {
  companion object {
    fun start(context: Context) {
      context.startActivity(Intent(context, ImagePickerActivity::class.java))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.image_picker_activity)
  }
}
