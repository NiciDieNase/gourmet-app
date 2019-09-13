package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class NewsDao : BaseDao<News>() {

    @Query("SELECT * FROM news WHERE locationId = :locationId")
    abstract fun getNewsForLocation(locationId: Long): LiveData<News>
}
