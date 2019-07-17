package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class AdditiveDao : BaseDao<Additive>() {

    @Query("SELECT * FROM Additive")
    abstract fun getAll(): LiveData<List<Additive>>

    @Query("SELECT * FROM Additive WHERE additiveId IN (:ids)")
    abstract suspend fun getByIds(ids: List<Int>): List<Additive>
}