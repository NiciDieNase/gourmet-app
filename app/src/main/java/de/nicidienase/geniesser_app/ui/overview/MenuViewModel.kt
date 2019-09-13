package de.nicidienase.geniesser_app.ui.overview

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.PreferencesService
import de.nicidienase.geniesser_app.data.MenuRepository

class MenuViewModel(
    private val menuRepository: MenuRepository,
    private val preferencesService: PreferencesService
) : ViewModel() {

    fun getDishesForDay(day: Long) = menuRepository.getDishesForDayAndLocation(day, preferencesService.currentLocation)

    fun updateDishes() {
        menuRepository.update(preferencesService.currentLocation)
    }

    fun getAvailableDays() = menuRepository.getDays(preferencesService.currentLocation)

    val isRefreshing = menuRepository.isRefreshing
}