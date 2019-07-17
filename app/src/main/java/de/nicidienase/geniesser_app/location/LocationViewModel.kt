package de.nicidienase.geniesser_app.location

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.LocationDao

class LocationViewModel(val locationDao: LocationDao,
                        val preferencesService: PreferencesService): ViewModel() {

    fun getLocations() = locationDao.getAll()

    fun selectLocation(location: Location) {
        preferencesService.currentLocation = location.locationId
    }
}