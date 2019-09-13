package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.Date

@Dao
abstract class DishDao : BaseDao<Dish>() {

    @Query("SELECT * FROM Dish")
    abstract fun getAll(): LiveData<List<Dish>>

    @Query("SELECT * FROM Dish WHERE locationId = :locationId ORDER BY orderIndex")
    abstract fun getAllForLocation(locationId: Long): LiveData<List<Dish>>

    @Query("SELECT DISTINCT date FROM Dish ORDER BY date ASC")
    abstract fun getAvailableDates(): LiveData<List<Date>>

    @Query("SELECT DISTINCT date FROM Dish WHERE locationId=:locationId ORDER BY date ASC")
    abstract fun getAvailableDatesForLocation(locationId: Long): LiveData<List<Date>>

    @Query("SELECT * FROM Dish WHERE date = :day ORDER BY orderIndex")
    abstract fun getDishesForDay(day: Long): LiveData<List<Dish>>

    @Query("SELECT * FROM Dish WHERE date = :day AND locationId = :locationId ORDER BY orderIndex")
    abstract fun getDishesForDayAndLocation(day: Long, locationId: Long): LiveData<List<Dish>>
}