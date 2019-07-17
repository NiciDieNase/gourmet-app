package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
abstract class DishDao: BaseDao<Dish>() {

    @Query("SELECT * FROM Dish")
    abstract fun getAll(): LiveData<List<Dish>>

    @Query("SELECT * FROM Dish WHERE locationId = :locationId")
    abstract fun getAllForLocation(locationId: Int): LiveData<List<Dish>>

    @Query("SELECT DISTINCT date FROM Dish ORDER BY date ASC")
    abstract fun getAvailableDates(): LiveData<List<Date>>

    @Query("SELECT * FROM Dish WHERE date = :day")
    abstract fun getDishesForDay(day: Long): LiveData<List<Dish>>
}