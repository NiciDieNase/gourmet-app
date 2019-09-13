package de.nicidienase.geniesser_app

import android.content.SharedPreferences

class PreferencesService(private val sharedPreferences: SharedPreferences) {

    var currentLocation: Long
        get() = sharedPreferences.getLong(KEY_LOCATION, 3317)
        set(value) = sharedPreferences.edit().putLong(KEY_LOCATION, value).apply()

    companion object {
        private const val KEY_LOCATION = "LocationLong"
    }
}