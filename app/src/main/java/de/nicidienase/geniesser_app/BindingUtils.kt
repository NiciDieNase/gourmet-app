package de.nicidienase.geniesser_app

import android.os.Build
import android.text.Html
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide

@BindingAdapter("price")
fun AppCompatTextView.setPrice(value: Int) {
    val preferencesService = PreferencesService(
        PreferenceManager.getDefaultSharedPreferences(context)
    )

    val floatPrice = if (preferencesService.priceExternal) {
        1.5 * value / 100.0
    } else {
        value / 100.0
    }
    this.text = String.format("%.2f €", floatPrice)
}

@BindingAdapter("price")
fun AppCompatTextView.setPrice(value: Float) {
    this.text = String.format("%.2f €", value)
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
