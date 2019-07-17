package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import de.nicidienase.geniesser_app.api.MenuApi
import de.nicidienase.geniesser_app.api.SpeiseplanWrapperDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuRepository(
    val api: MenuApi,
    val dishDao: DishDao,
    val allergenDao: AllergenDao,
    val additiveDao: AdditiveDao,
    val propertyDao: PropertyDao
) {

    fun getDishes() = dishDao.getAll()

    fun update() = GlobalScope.launch {
        val menu: SpeiseplanWrapperDto = api.getMenu(3317)[0]
        val dishes = menu.speiseplanGerichtData.map { Dish.fromGerichtDto(it) }.filterNotNull()
        dishDao.insert( *(dishes.toTypedArray()) )
    }
}
