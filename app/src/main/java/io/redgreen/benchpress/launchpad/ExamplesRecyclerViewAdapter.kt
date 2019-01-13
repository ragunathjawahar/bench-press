package io.redgreen.benchpress.launchpad

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.redgreen.benchpress.R
import kotlinx.android.synthetic.main.example_list_item.view.*

class ExamplesRecyclerViewAdapter(
    private val examples: List<Example>
) : RecyclerView.Adapter<ExampleViewHolder>() {
  private lateinit var layoutInflater: LayoutInflater

  override fun onCreateViewHolder(parent: ViewGroup, position: Int): ExampleViewHolder {
    if (!::layoutInflater.isInitialized) {
      layoutInflater = LayoutInflater.from(parent.context)
    }

    val itemView = layoutInflater.inflate(R.layout.example_list_item, parent, false)
    return ExampleViewHolder(itemView)
  }

  override fun onBindViewHolder(
      holder: ExampleViewHolder,
      position: Int
  ) {
    holder.bind(examples[position])
  }

  override fun getItemCount(): Int =
      examples.size
}

class ExampleViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
  fun bind(example: Example) {
    with(itemView) {
      exampleTextView.text = example.title
      setOnClickListener { example.starter(itemView.context) }
    }
  }
}
