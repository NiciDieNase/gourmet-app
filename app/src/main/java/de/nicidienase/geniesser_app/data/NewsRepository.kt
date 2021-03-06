package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.nicidienase.geniesser_app.api.GourmetApi
import de.nicidienase.geniesser_app.api.NewsDto
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
class NewsRepository(
    private val api: GourmetApi,
    private val newsDao: NewsDao
) {

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    fun getNewsForLocation(locationId: Long, activeOnly: Boolean = false): LiveData<List<News>> {
        return if (activeOnly) {
            newsDao.getActiveNewsForLocation(locationId)
        } else {
            newsDao.getNewsForLocation(locationId)
        }
    }

    fun update(locationId: Long) = GlobalScope.launch {
        Timber.i("Starting News update for locationId $locationId")
        _isRefreshing.postValue(true)

        val existingNews = newsDao.getNewsForLocationSync(locationId)
        val oldBackendIds = existingNews.map { it.backendId }
        val backendNews: List<NewsDto> = api.getNews(locationId)
        val news: List<News> =
            backendNews.mapNotNull { News.fromNewsDto(it, locationId.toLong()) }

        val newsToUpdate = news.filter { oldBackendIds.contains(it.backendId) }
        newsToUpdate.forEach { newsItem ->
            val first = existingNews.first { it.backendId == newsItem.backendId }
            newsItem.id = first.id
            newsItem.newNews = first.newNews
        }

        val newsToInsert = news.filterNot { oldBackendIds.contains(it.backendId) }
        newsToInsert.forEach { it.newNews = true }

        newsDao.insert(newsToInsert)
        newsDao.update(* newsToUpdate.toTypedArray())

        val backendNewsIds = news.map { it.backendId }
        val outdatedNews = existingNews.filterNot { backendNewsIds.contains(it.backendId) }
        outdatedNews.forEach { it.active = false }
        newsDao.update(* outdatedNews.toTypedArray())

        _isRefreshing.postValue(false)
        Timber.i("News update for locationId $locationId is done")
    }

    fun newsCountForLocation(locationId: Long) = newsDao.getNewsCountForLocation(locationId)

    fun setNewsForLocationOld(locationId: Long) = GlobalScope.launch {
        newsDao.setNewsOldForLocation(locationId)
    }

    fun deleteInactiveNews() = GlobalScope.launch {
        newsDao.deleteInactiveNews()
    }

    companion object {
        private val TAG = NewsRepository::class.java.simpleName
    }
}
