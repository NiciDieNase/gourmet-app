package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class NewsDao : BaseDao<News>() {

    @Query("SELECT * FROM news WHERE locationId = :locationId ORDER BY date DESC")
    abstract fun getNewsForLocation(locationId: Long): LiveData<List<News>>

    @Query("SELECT title FROM news WHERE locationId = :locationId")
    abstract fun getNewsTitlesForLocationSync(locationId: Long): List<String>

    @Query("SELECT * FROM news WHERE locationId = :locationId")
    abstract fun getNewsForLocationSync(locationId: Long): List<News>

    @Query("SELECT count(*) FROM news WHERE locationId = :locationId")
    abstract fun getNewsCountForLocation(locationId: Long): LiveData<Int>
}
