package de.nicidienase.geniesser_app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.R

class PrefFragment() : PreferenceFragmentCompat() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.preferences_menu, menu)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val locationPreference = findPreference<Preference>("location")
        locationPreference?.setOnPreferenceClickListener {
            findNavController().navigate(PrefFragmentDirections.actionPrefFragmentToLocationSelectFragment())
            true
        }
        val preferencesService: PreferencesService = PreferencesService(
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        )
        locationPreference?.summary =
            "${preferencesService.currentLocationName} (${preferencesService.currentLocation})"
    }
}
