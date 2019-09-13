package de.nicidienase.geniesser_app

import android.os.Build
import android.text.Html
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("price")
fun AppCompatTextView.setPrice(value: Int) {
    val floatPrice = value / 100.0
    this.text = String.format("%.2f €", floatPrice)
}

@BindingAdapter("srcCompat")
fun AppCompatImageView.setSrc(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}

@BindingAdapter("textHtml")
fun AppCompatTextView.setTextHtml(html: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        @Suppress("DEPRECATION")
        text = Html.fromHtml(html)
    }
}