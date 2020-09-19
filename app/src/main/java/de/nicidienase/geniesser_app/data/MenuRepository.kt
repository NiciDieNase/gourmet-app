package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.api.GourmetApi
import java.util.Calendar
import java.util.Date
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuRepository(
    private val api: GourmetApi,
    private val database: GourmetDatabase,
    private val preferencesService: PreferencesService
) {

    private val dishDao by lazy { database.getDishDao() }
    private val locationDao by lazy { database.getLocationDao() }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getDishesForDayAndLocation(day: Long, locationId: Long): LiveData<List<Dish>> {
        return if (preferencesService.hideOldMenu) {
            dishDao.getActiveDishesForDayAndLocation(day, locationId)
        } else {
            dishDao.getDishesForDayAndLocation(day, locationId)
        }
    }

    fun getDays(locationId: Long): LiveData<List<Date>> {
        if (preferencesService.hideOldMenu) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -1)
            val date = calendar.time.time
            return dishDao.getAvailableActiveDatesForLocation(locationId, date)
        } else {
            return dishDao.getAvailableDatesForLocation(locationId)
        }
    }

    fun update(locationId: Long) = GlobalScope.launch {
        _isRefreshing.postValue(true)
        locationDao.insert(api.getLocations().mapNotNull { Location.fromDto(it) })

        val menus = api.getMenu(locationId)
        val categories = api.getMenuCategories(locationId)
        val dishes: List<Dish>? = menus?.flatMap { wrapper ->
            wrapper.speiseplanGerichtData?.mapNotNull {
                Dish.fromGerichtDto(it, locationId, wrapper.speiseplanAdvanced, categories)
            } ?: emptyList()
        }
        if (dishes != null) {
            val existingItems = dishDao.getAllForLocationSync(locationId)
            val newItems = dishes.map { it.dishId to it }.toMap()
            val oldBackendIds = existingItems.map { it.dishId }
            val newBackendIds = dishes.map { it.dishId }

            var itemsToUpdate = existingItems.filter { newBackendIds.contains(it.dishId) }

            itemsToUpdate =
                itemsToUpdate.mapNotNull {
                    it.update(newItems[it.dishId]!!) // can be !! because we filtered for the same ids before
                }

            val itemsToInsert = dishes.filterNot { oldBackendIds.contains(it.dishId) }

            dishDao.insert(itemsToInsert)
            dishDao.update(*itemsToUpdate.toTypedArray())

            val outdatedItems = existingItems.filterNot { newBackendIds.contains(it.dishId) }
            outdatedItems.forEach {
                it.active = false
            }
            dishDao.update(* outdatedItems.toTypedArray())
        }
        _isRefreshing.postValue(false)
    }
}
