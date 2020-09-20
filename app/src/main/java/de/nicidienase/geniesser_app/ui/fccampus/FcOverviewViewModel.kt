package de.nicidienase.geniesser_app.ui.fccampus

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.nicidienase.geniesser_app.data.FcRepository
import de.nicidienase.geniesser_app.util.CalendarUtils
import java.util.Date
import kotlinx.coroutines.launch

class FcOverviewViewModel(
    private val fcRepository: FcRepository
) : ViewModel() {
    var selectedDay: Date? = null
    val availableDays: LiveData<List<Date>> = fcRepository.availableDays

    fun updateMenu() = viewModelScope.launch {
        val startDate = CalendarUtils.getDateForDaysInFuture(-7)
        val endDate = CalendarUtils.getDateForDaysInFuture(7)
        fcRepository.updateMenu(startDate, endDate)
    }
}
