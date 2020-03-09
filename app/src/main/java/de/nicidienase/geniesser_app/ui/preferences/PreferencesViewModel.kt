package de.nicidienase.geniesser_app.ui.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.LocationDao

class PreferencesViewModel(private val preferencesService: PreferencesService,
                           private val locationDao: LocationDao): ViewModel() {

    val currentLocation: LiveData<Location> = Transformations.switchMap(preferencesService.currentLocationLivedata) {
        locationDao.getLocationForLocationId(it)
    }

    val currentLocationName: LiveData<String> = Transformations.map(currentLocation) {
        "${it.name} (${it.locationId})"
    }
}