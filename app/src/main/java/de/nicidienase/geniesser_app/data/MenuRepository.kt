package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.nicidienase.geniesser_app.api.MenuApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuRepository(
    private val api: MenuApi,
    private val database: FoodDatabase
) {

    private val dishDao by lazy { database.getDishDao() }
    private val locationDao by lazy { database.getLocationDao() }

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getDishesForLocation(locationId: Int) = dishDao.getAllForLocation(locationId)
    fun getDishesForDay(day: Long): LiveData<List<Dish>> = dishDao.getDishesForDay(day)
    fun getDays(locationId: Int) = dishDao.getAvailableDatesForLocation(locationId)

    fun update(locationId: Int) = GlobalScope.launch {
        _isRefreshing.postValue(true)
        locationDao.insert(api.getLocations().mapNotNull { Location.fromDto(it) })

        val menus = api.getMenu(locationId)
        val categories = api.getMenuCategories(locationId)
        val dishes: List<Dish> = menus.flatMap { it.speiseplanGerichtData.mapNotNull { Dish.fromGerichtDto(it, locationId, categories) } }
        dishDao.insert(dishes)
        _isRefreshing.postValue(false)
    }
}
