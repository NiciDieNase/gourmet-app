package de.nicidienase.geniesser_app.ui.fccampus.mealtimes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.nicidienase.geniesser_app.data.fccampus.FcRepository
import de.nicidienase.geniesser_app.util.CalendarUtils
import kotlinx.coroutines.launch
import java.util.Date

class MealTimesViewModel(private val fcRepository: FcRepository) : ViewModel() {

    fun getMealTimes() = fcRepository.getMealTimesFromWeek(CalendarUtils.getWeekNumberForDate(Date()))

    fun updateMealTimes() = viewModelScope.launch {
        fcRepository.updateMealTimes()
    }
}
