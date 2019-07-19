package de.nicidienase.geniesser_app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.nicidienase.geniesser_app.api.buildMenuApi
import de.nicidienase.geniesser_app.data.FoodDatabase
import de.nicidienase.geniesser_app.data.MenuRepository
import de.nicidienase.geniesser_app.location.LocationViewModel
import de.nicidienase.geniesser_app.overview.MenuViewModel

class GourmetViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val database = FoodDatabase.build(context.applicationContext)

    private val preferencesService = PreferencesService(context.getSharedPreferences("gourmet_preferences", Context.MODE_PRIVATE))

    private val menuApi by lazy { buildMenuApi() }

    private val menuRepository: MenuRepository = MenuRepository(menuApi, database)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(menuRepository, preferencesService) as T
        } else if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(database.getLocationDao(), preferencesService) as T
        } else {
            throw UnsupportedOperationException(
                "The requested ViewModel is currently unsupported. " +
                        "Please make sure to implement are correct creation of it. " +
                        " Request: ${modelClass.canonicalName}"
            )
        }
    }
}