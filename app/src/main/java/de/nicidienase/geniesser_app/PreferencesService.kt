package de.nicidienase.geniesser_app

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import de.nicidienase.geniesser_app.util.longLiveData

class PreferencesService(private val sharedPreferences: SharedPreferences) {

    var currentLocation: Long
        get() = sharedPreferences.getLong(KEY_LOCATION, 3317)
        set(value) = sharedPreferences.edit().putLong(KEY_LOCATION, value).apply()

    var currentOutlet: Long
        get() = sharedPreferences.getLong(KEY_OUTLET, 32)
        set(value) = sharedPreferences.edit().putLong(KEY_OUTLET, value).apply()

    var hideOldMenu: Boolean
        get() = sharedPreferences.getBoolean(KEY_HIDE_MENU, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_HIDE_MENU, value).apply()

    var priceExternal: Boolean
        get() = sharedPreferences.getBoolean(KEY_PRICE_EXTERNAL, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_PRICE_EXTERNAL, value).apply()

    val currentLocationLivedata: LiveData<Long> = sharedPreferences.longLiveData(KEY_LOCATION, 3317)
    val currentOutletLiveData: LiveData<Long> = sharedPreferences.longLiveData(KEY_OUTLET, 0)

    companion object {
        private const val KEY_LOCATION = "LocationLong"
        private const val KEY_OUTLET = "OutletLong"
        private const val KEY_LOCATION_NAME = "LocationString"
        private const val KEY_HIDE_MENU = "HideMenuBool"
        private const val KEY_PRICE_EXTERNAL = "PriceExternalBool"
    }
}
