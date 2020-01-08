package de.nicidienase.geniesser_app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.nicidienase.geniesser_app.api.GourmetApi
import de.nicidienase.geniesser_app.data.GourmetDatabase
import de.nicidienase.geniesser_app.data.MenuRepository
import de.nicidienase.geniesser_app.data.NewsRepository
import de.nicidienase.geniesser_app.ui.location.LocationViewModel
import de.nicidienase.geniesser_app.ui.news.NewsViewModel
import de.nicidienase.geniesser_app.ui.overview.MenuViewModel
import de.nicidienase.geniesser_app.util.SingletonHolder

class GourmetViewModelFactory private constructor(context: Context) : ViewModelProvider.Factory {

    private val database = GourmetDatabase.build(context.applicationContext)

    private val preferencesService = PreferencesService(
        context.getSharedPreferences(
            "gourmet_preferences",
            Context.MODE_PRIVATE
        )
    )

    private val menuApi by lazy { GourmetApi.instance }

    private val menuRepository: MenuRepository by lazy { MenuRepository(menuApi, database) }

    private val newsRepository: NewsRepository by lazy {
        NewsRepository(
            menuApi,
            database.getNewsDao()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(menuRepository, preferencesService) as T
        } else if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(
                database.getLocationDao(),
                preferencesService,
                newsRepository
            ) as T
        } else if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository, preferencesService) as T
        } else if (modelClass.isAssignableFrom(GourmetActivityViewModel::class.java)) {
            return GourmetActivityViewModel(newsRepository, menuRepository, preferencesService) as T
        } else {
            throw UnsupportedOperationException(
                "The requested ViewModel is currently unsupported. " +
                    "Please make sure to implement are correct creation of it. " +
                    " Request: ${modelClass.canonicalName}"
            )
        }
    }

    companion object : SingletonHolder<GourmetViewModelFactory, Context>(::GourmetViewModelFactory)
}