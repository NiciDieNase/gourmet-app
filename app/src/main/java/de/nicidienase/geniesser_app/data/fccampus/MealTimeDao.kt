package de.nicidienase.geniesser_app.data.fccampus

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.nicidienase.geniesser_app.data.BaseDao

@Dao
abstract class MealTimeDao: BaseDao<MealTime>() {

    @Query("SELECT * FROM MealTime ORDER BY calendarWeek, description ASC")
    abstract fun getAll(): LiveData<MealTime>

    @Query("SELECT * FROM MealTime WHERE calendarWeek >= :week ORDER BY calendarWeek, description ASC")
    abstract fun getFromWeekIncluding(week: Int): LiveData<List<MealTime>>

    @Query("DELETE FROM MealTime WHERE calendarWeek < :week")
    abstract fun deleteBeforeWeek(week: Int)
}