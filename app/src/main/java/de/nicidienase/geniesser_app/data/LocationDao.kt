package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class LocationDao : BaseDao<Location>() {

    @Query("SELECT * FROM Location ORDER BY name COLLATE NOCASE ASC")
    abstract fun getAll(): LiveData<List<Location>>
}