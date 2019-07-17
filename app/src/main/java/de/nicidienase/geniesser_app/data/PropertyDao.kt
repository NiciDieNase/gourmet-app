package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class PropertyDao: BaseDao<Property>() {

    @Query("SELECT * FROM Property")
    abstract fun getAll(): LiveData<List<Property>>

    @Query("SELECT * FROM Property WHERE propertyId IN (:ids)")
    abstract suspend fun getByIds(ids: List<Int>): List<Property>
}