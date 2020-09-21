package de.nicidienase.geniesser_app

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import de.nicidienase.geniesser_app.api.GourmetApi
import de.nicidienase.geniesser_app.api.fccampus.FcCampusApi
import de.nicidienase.geniesser_app.data.FcRepository
import de.nicidienase.geniesser_app.data.GourmetDatabase
import de.nicidienase.geniesser_app.data.MenuRepository
import de.nicidienase.geniesser_app.data.NewsRepository
import de.nicidienase.geniesser_app.ui.fccampus.FcMenuViewModel
import de.nicidienase.geniesser_app.ui.fccampus.FcOverviewViewModel
import de.nicidienase.geniesser_app.ui.location.LocationViewModel
import de.nicidienase.geniesser_app.ui.news.NewsViewModel
import de.nicidienase.geniesser_app.ui.overview.MenuViewModel
import de.nicidienase.geniesser_app.ui.preferences.PreferencesViewModel
import de.nicidienase.geniesser_app.util.SingletonHolder

class GourmetViewModelFactory private constructor(context: Context) : ViewModelProvider.Factory {

    private val database = GourmetDatabase.build(context.applicationContext)

    private val preferencesService = PreferencesService(
        PreferenceManager.getDefaultSharedPreferences(context)
    )

    private val menuApi by lazy { GourmetApi.instance }

    private val fcApi by lazy { FcCampusApi.instance }

    private val menuRepository: MenuRepository by lazy {
        MenuRepository(
            menuApi,
            database,
            preferencesService
        )
    }

    private val newsRepository: NewsRepository by lazy {
        NewsRepository(
            menuApi,
            database.getNewsDao()
        )
    }

    private val fcRepository: FcRepository by lazy {
        FcRepository(
            fcApi,
            database.getFcMealDao(),
            preferencesService
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MenuViewModel::class.java) -> MenuViewModel(
                menuRepository,
                preferencesService
            ) as T
            modelClass.isAssignableFrom(LocationViewModel::class.java) -> LocationViewModel(
                database.getLocationDao(),
                preferencesService,
                newsRepository
            ) as T
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> NewsViewModel(
                newsRepository,
                preferencesService
            ) as T
            modelClass.isAssignableFrom(GourmetActivityViewModel::class.java) -> GourmetActivityViewModel(
                newsRepository,
                menuRepository,
                preferencesService
            ) as T
            modelClass.isAssignableFrom(PreferencesViewModel::class.java) -> PreferencesViewModel(
                preferencesService,
                database.getLocationDao()
            ) as T
            modelClass.isAssignableFrom(FcMenuViewModel::class.java) -> FcMenuViewModel(fcRepository) as T
            modelClass.isAssignableFrom(FcOverviewViewModel::class.java) -> FcOverviewViewModel(
                fcRepository,
                preferencesService
            ) as T
            else -> throw UnsupportedOperationException(
                "The requested ViewModel is currently unsupported. Please make sure to implement are correct creation of it. Request: ${modelClass.canonicalName}"
            )
        }
    }

    companion object : SingletonHolder<GourmetViewModelFactory, Context>(::GourmetViewModelFactory)
}
