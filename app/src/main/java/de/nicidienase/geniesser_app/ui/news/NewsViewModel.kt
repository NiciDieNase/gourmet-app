package de.nicidienase.geniesser_app.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.News
import de.nicidienase.geniesser_app.data.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {

    val updating = newsRepository.isRefreshing

    val news: LiveData<List<News>> = Transformations.switchMap(preferencesService.hideOldNews) {
        newsRepository.getNewsForLocation(preferencesService.currentLocation, it)
    }

    fun update() = newsRepository.update(preferencesService.currentLocation)
    fun seen() = newsRepository.setNewsForLocationOld(preferencesService.currentLocation)
    fun cleanup() = newsRepository.deleteInactiveNews()
}
