package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.nicidienase.geniesser_app.api.GourmetApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsRepository(
    private val api: GourmetApi,
    private val newsDao: NewsDao
) {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun update(locationId: Int) = GlobalScope.launch {
        _isRefreshing.postValue(true)

        val news: List<News> = api.getNews(locationId).mapNotNull { News.fromNewsDto(it, locationId.toLong()) }

        newsDao.insert(news)

        _isRefreshing.postValue(false)
    }
}