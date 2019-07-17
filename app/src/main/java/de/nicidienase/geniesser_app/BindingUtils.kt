package de.nicidienase.geniesser_app

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("price")
fun TextView.setPrice(value: Int){
    val floatPrice = value / 100.0
    this.text = String.format("%.2f €", floatPrice)
}