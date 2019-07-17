package de.nicidienase.geniesser_app

import android.content.SharedPreferences

class PreferencesService(private val sharedPreferences: SharedPreferences) {

    var currentLocation: Int
        get() = sharedPreferences.getInt(KEY_LOCATION, 3317)
        set(value) = sharedPreferences.edit().putInt(KEY_LOCATION, value).apply()


    companion object {
        private const val KEY_LOCATION = "Location"
    }
}