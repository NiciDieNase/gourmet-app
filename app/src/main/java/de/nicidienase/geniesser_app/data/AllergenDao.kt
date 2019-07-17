package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class AllergenDao: BaseDao<Allergen>() {

    @Query("SELECT * FROM Allergen")
    abstract fun getAll(): LiveData<List<Allergen>>

    @Query("SELECT * FROM Allergen WHERE allergenId IN (:ids)")
    abstract suspend fun getByIds(ids: List<Int>): List<Allergen>
}