package de.nicidienase.geniesser_app.data

import android.util.Log
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

    fun getNewsForLocation(locationId: Long) = newsDao.getNewsForLocation(locationId)

    fun update(locationId: Long) = GlobalScope.launch {
        Log.i(TAG, "Starting News update for locationId $locationId")
        _isRefreshing.postValue(true)

        val existingNews = newsDao.getNewsForLocationSync(locationId)
        val oldBackendIds = existingNews.map { it.backendId }
        val news: List<News> = api.getNews(locationId).mapNotNull { News.fromNewsDto(it, locationId.toLong()) }

        val newsToUpdate = news.filter { oldBackendIds.contains(it.backendId) }
        newsToUpdate.forEach { news ->
            news.id = existingNews.first { it.backendId == news.backendId }.id
        }

        val newsToInsert = news.filterNot { oldBackendIds.contains(it.backendId) }

        newsDao.insert(newsToInsert)
        newsDao.update(* newsToUpdate.toTypedArray())

        _isRefreshing.postValue(false)
        Log.i(TAG, "News update for locationId $locationId is done")
    }

    fun newsCountForLocation(locationId: Long) = newsDao.getNewsCountForLocation(locationId)

    companion object {
        private val TAG = NewsRepository::class.java.simpleName
    }
}