package de.nicidienase.geniesser_app.ui

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import de.nicidienase.geniesser_app.R

class PrefFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        findPreference<Preference>("location")?.setOnPreferenceClickListener {
            findNavController().navigate(PrefFragmentDirections.actionPrefFragmentToLocationSelectFragment())
            true
        }
    }
}
