package de.nicidienase.geniesser_app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.nicidienase.geniesser_app.api.buildMenuApi
import de.nicidienase.geniesser_app.data.FoodDatabase
import de.nicidienase.geniesser_app.data.MenuRepository

class GourmetViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val database = FoodDatabase.build(context.applicationContext)

    private val menuApi by lazy { buildMenuApi() }

    private val menuRepository: MenuRepository = MenuRepository(
        menuApi,
        database.getDishDao(),
        database.getAllergenDao(),
        database.getAdditiveDao(),
        database.getPropertyDao()
    )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(menuRepository) as T
        } else {
            throw UnsupportedOperationException(
                "The requested ViewModel is currently unsupported. " +
                        "Please make sure to implement are correct creation of it. " +
                        " Request: ${modelClass.canonicalName}"
            )
        }
    }
}