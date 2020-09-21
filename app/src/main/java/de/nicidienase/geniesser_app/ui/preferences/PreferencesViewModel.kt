package de.nicidienase.geniesser_app.ui.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.LocationDao
import de.nicidienase.geniesser_app.data.Outlet
import de.nicidienase.geniesser_app.data.OutletDao

class PreferencesViewModel(
    private val preferencesService: PreferencesService,
    private val locationDao: LocationDao,
    private val outletDao: OutletDao
) : ViewModel() {

    private val currentLocation: LiveData<Location> = Transformations.switchMap(preferencesService.currentLocationLivedata) {
        locationDao.getLocationForLocationId(it)
    }

    val currentLocationName: LiveData<String> = Transformations.map(currentLocation) {
        "${it.name} (${it.locationId})"
    }

    val currentOutlet: LiveData<Outlet> = Transformations.switchMap(preferencesService.currentOutletLiveData) {
        outletDao.getOutletById(it)
    }

    val currentOutletName: LiveData<String> = Transformations.map(currentOutlet) {
        "${it.name} (${it.locationId}-${it.outletId})"
    }
}
