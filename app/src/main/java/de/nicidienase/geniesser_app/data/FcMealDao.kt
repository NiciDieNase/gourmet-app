package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import java.util.Date

@Dao
abstract class FcMealDao : BaseDao<FcMeal>() {

    @Query("SELECT * FROM FcMeal")
    abstract fun getAll(): LiveData<FcMeal>

    @Query("SELECT * FROM FcMeal WHERE date = :day")
    abstract fun getMealsForDay(day: Long): LiveData<List<FcMeal>>

    @Query("SELECT DISTINCT date FROM FcMeal ORDER BY date ASC")
    abstract fun getAvailableDates(): LiveData<List<Date>>
}
