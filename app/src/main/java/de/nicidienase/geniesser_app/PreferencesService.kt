package de.nicidienase.geniesser_app

import android.content.SharedPreferences

class PreferencesService(private val sharedPreferences: SharedPreferences) {

    var currentLocation: Long
        get() = sharedPreferences.getLong(KEY_LOCATION, 3317)
        set(value) = sharedPreferences.edit().putLong(KEY_LOCATION, value).apply()

    var hideOldMenu: Boolean
        get() = sharedPreferences.getBoolean(KEY_HIDE_MENU, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_HIDE_MENU, value).apply()

    var priceExternal: Boolean
        get() = sharedPreferences.getBoolean(KEY_PRICE_EXTERNAL, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_PRICE_EXTERNAL, value).apply()

    companion object {
        private const val KEY_LOCATION = "LocationLong"
        private const val KEY_HIDE_MENU = "HideMenuBool"
        private const val KEY_PRICE_EXTERNAL = "PriceExternalBool"
    }
}