package de.nicidienase.geniesser_app.ui.preferences

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.R

class PreferencesFragment() : PreferenceFragmentCompat() {

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.preferences_menu, menu)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationPreference = findPreference<Preference>("location")
        locationPreference?.setOnPreferenceClickListener {
            findNavController().navigate(PreferencesFragmentDirections.actionPrefFragmentToLocationSelectFragment())
            true
        }

        val viewModel: PreferencesViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

        viewModel.currentLocationName.observe(viewLifecycleOwner, Observer {
            locationPreference?.summary = it
        })
    }
}
