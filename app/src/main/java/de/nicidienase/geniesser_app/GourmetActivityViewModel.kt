package de.nicidienase.geniesser_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.data.NewsRepository

class GourmetActivityViewModel(
    private val newsRepository: NewsRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {

    val newsCount: LiveData<Long>
            get() = newsRepository.newsCountForLocation(preferencesService.currentLocation.toLong())
}