package de.nicidienase.geniesser_app.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.MenuRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MenuViewModel(
    private val menuRepository: MenuRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {

    fun getDishesForDay(day: Long) = menuRepository.getDishesForDay(day)

    fun updateDishes() {
        menuRepository.update(preferencesService.currentLocation)
    }

    fun getAvailableDays() = menuRepository.getDays(preferencesService.currentLocation)

    val isRefreshing = menuRepository.isRefreshing
}