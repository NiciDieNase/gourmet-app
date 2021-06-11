package de.nicidienase.geniesser_app.data.fccampus

import androidx.lifecycle.LiveData
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.api.fccampus.FcCampusApi
import de.nicidienase.geniesser_app.util.CalendarUtils
import java.util.Calendar
import java.util.Date
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FcRepository(
    private val fcCampusApi: FcCampusApi,
    private val fcMealDao: FcMealDao,
    private val fcMealTimeDao: MealTimeDao,
    private val preferencesService: PreferencesService
) {
    val availableDays: LiveData<List<Date>> = fcMealDao.getAvailableDates()
    val allMeals: LiveData<FcMeal> = fcMealDao.getAll()

    suspend fun updateMenu(from: Date, to: Date) {
        withContext(Dispatchers.IO) {
            val fromDateString = CalendarUtils.formatDateForFcAPI(from)
            val toDateString = CalendarUtils.formatDateForFcAPI(to)

            val menus = fcCampusApi.getMenus(fromDateString, toDateString)

            menus.dayMenus.forEach {
                val menuDay = CalendarUtils.parseDateString(it.day) ?: Date(0)
                val meals = it.meals.mapNotNull { mealDto ->
                    FcMeal.fromMealDto(mealDto, menuDay)
                }
                val ids = fcMealDao.insert(meals)
                Timber.d("Inserted ${ids.size} entries")
            }
        }
    }

    suspend fun updateMealTimes() {
        withContext(Dispatchers.IO){
            val currentWeek = CalendarUtils.getWeekNumberForDate(Date())

            updateMealTimesForWeek(currentWeek)
            updateMealTimesForWeek(currentWeek + 1)
            updateMealTimesForWeek(currentWeek + 2)

            fcMealTimeDao.deleteBeforeWeek(currentWeek)
        }
    }

    private suspend fun updateMealTimesForWeek(week: Int){
        withContext(Dispatchers.IO){
            val apiMealTimes = fcCampusApi.getMealTimes(week)
            val mealTimes: List<MealTime> = apiMealTimes.mealTimes.mapNotNull { MealTime.fromMealTimeDto(it) }
            fcMealTimeDao.insert(mealTimes)
        }

    }

    fun getDays(): LiveData<List<Date>> {
        return if (preferencesService.hideOldMenu) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, -1)
            val date = calendar.time.time
            fcMealDao.getAvailableDates(date)
        } else {
            fcMealDao.getAvailableDates()
        }
    }

    fun getMealsForDay(day: Long): LiveData<List<FcMeal>> {
        return fcMealDao.getMealsForDay(day)
    }

    fun getMealTimesFromWeek(week: Int) = fcMealTimeDao.getFromWeekIncluding(week)

    companion object {
        private val TAG = FcRepository::class.java.simpleName
    }
}
