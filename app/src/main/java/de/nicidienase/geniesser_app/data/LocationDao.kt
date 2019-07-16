package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class LocationDao: BaseDao<Location>() {

    @Query("SELECT * FROM Location")
    abstract fun getAll(): LiveData<List<Location>>
}