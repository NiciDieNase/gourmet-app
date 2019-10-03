package de.nicidienase.geniesser_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.data.MenuRepository
import de.nicidienase.geniesser_app.data.NewsRepository

class GourmetActivityViewModel(
    private val newsRepository: NewsRepository,
    private val menuRepository: MenuRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {
    fun update() {
        newsRepository.update(preferencesService.currentLocation)
        menuRepository.update(preferencesService.currentLocation)
    }

    val newsCount: LiveData<Int>
        get() = newsRepository.newsCountForLocation(preferencesService.currentLocation)
}