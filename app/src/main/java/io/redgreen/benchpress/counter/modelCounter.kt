package io.redgreen.benchpress.counter
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelCounter(
    val counter: Int
) : Parcelable {

    companion object {
        val ZERO = ModelCounter(0)
    }

    fun increment(): ModelCounter = copy(counter = counter + 1)

    fun decrement(): ModelCounter = copy(counter = if (counter == 0) 0 else counter - 1)

}