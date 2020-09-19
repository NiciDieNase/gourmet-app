package de.nicidienase.geniesser_app.ui.location

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.LocationDao
import de.nicidienase.geniesser_app.data.NewsRepository

class LocationViewModel(
    private val locationDao: LocationDao,
    private val preferencesService: PreferencesService,
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getLocations() = locationDao.getAll()

    fun selectLocation(location: Location) {
        preferencesService.currentLocation = location.locationId
        newsRepository.update(location.locationId)
    }
}
