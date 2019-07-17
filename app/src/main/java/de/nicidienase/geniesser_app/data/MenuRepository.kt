package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import de.nicidienase.geniesser_app.api.MenuApi
import de.nicidienase.geniesser_app.api.SpeiseplanWrapperDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuRepository(
    private val api: MenuApi,
    private val database: FoodDatabase
) {

    private val dishDao by lazy { database.getDishDao() }
    private val locationDao by lazy { database.getLocationDao() }

    fun getDishes() = dishDao.getAll()
    fun getDishesForLocation(locationId: Int) = dishDao.getAllForLocation(locationId)
    fun getDishesForDay(day: Long): LiveData<List<Dish>> = dishDao.getDishesForDay(day)
    fun getDays(locationId: Int) = dishDao.getAvailableDatesForLocation(locationId)

    fun update(locationId: Int) = GlobalScope.launch {
        locationDao.insert(api.getLocations().mapNotNull { Location.fromDto(it) })

        val menu: SpeiseplanWrapperDto = api.getMenu(locationId)[0]
        val categories = api.getMenuCategories(locationId)
        dishDao.insert(menu.speiseplanGerichtData.mapNotNull { Dish.fromGerichtDto(it, locationId, categories) })
    }

}
