package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class PropertyDao: BaseDao<Property>() {

    @Query("SELECT * FROM Property")
    abstract fun getAll(): LiveData<List<Property>>
}