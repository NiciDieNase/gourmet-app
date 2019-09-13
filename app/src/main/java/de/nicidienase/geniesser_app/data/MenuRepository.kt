package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.nicidienase.geniesser_app.api.GourmetApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuRepository(
    private val api: GourmetApi,
    private val database: GourmetDatabase
) {

    private val dishDao by lazy { database.getDishDao() }
    private val locationDao by lazy { database.getLocationDao() }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getDishesForDayAndLocation(day: Long, locationId: Long) = dishDao.getDishesForDayAndLocation(day, locationId)
    fun getDays(locationId: Long) = dishDao.getAvailableDatesForLocation(locationId)

    fun update(locationId: Long) = GlobalScope.launch {
        _isRefreshing.postValue(true)
        locationDao.insert(api.getLocations().mapNotNull { Location.fromDto(it) })

        val menus = api.getMenu(locationId)
        val categories = api.getMenuCategories(locationId)
        val dishes: List<Dish>? = menus?.flatMap { wrapper ->
            wrapper.speiseplanGerichtData.mapNotNull {
                Dish.fromGerichtDto(it, locationId, wrapper.speiseplanAdvanced, categories)
            }
        }
        if (dishes != null) {
            dishDao.insert(dishes)
        }
        _isRefreshing.postValue(false)
    }
}
