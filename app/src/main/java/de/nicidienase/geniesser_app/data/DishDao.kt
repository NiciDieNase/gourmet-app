package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class DishDao: BaseDao<Dish>() {

    @Query("SELECT * FROM Dish")
    abstract fun getAll(): LiveData<List<Dish>>
}