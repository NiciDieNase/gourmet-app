package de.nicidienase.geniesser_app.ui.news

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.NewsRepository

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {

    val updating = newsRepository.isRefreshing

    fun getNews() = newsRepository.getNewsForLocation(preferencesService.currentLocation.toLong())
    fun update() = newsRepository.update(preferencesService.currentLocation)
    fun seen() = newsRepository.setNewsForLocationOld(preferencesService.currentLocation)
}
