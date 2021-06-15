package de.nicidienase.geniesser_app.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.LocationDao
import de.nicidienase.geniesser_app.data.NewsRepository
import de.nicidienase.geniesser_app.data.Outlet
import de.nicidienase.geniesser_app.data.OutletDao
import kotlinx.coroutines.launch

class LocationViewModel(
    private val locationDao: LocationDao,
    private val outletDao: OutletDao,
    private val preferencesService: PreferencesService,
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getLocations() = locationDao.getAll()

    fun getOutlets() = outletDao.getOutletsForLocation(preferencesService.currentLocation)

    fun selectLocation(location: Location) {
        preferencesService.currentLocation = location.locationId
        newsRepository.update(location.locationId)
        viewModelScope.launch {
            val firstOutletForLocation = outletDao.getFirstOutletForLocation(location.locationId)
            selectOutlet(firstOutletForLocation)
        }
    }

    fun selectOutlet(outlet: Outlet) {
        preferencesService.currentOutlet = outlet.outletId
    }
}
