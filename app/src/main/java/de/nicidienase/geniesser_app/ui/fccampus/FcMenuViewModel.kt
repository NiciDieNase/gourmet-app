package de.nicidienase.geniesser_app.ui.fccampus

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.data.FcRepository

class FcMenuViewModel(private val fcRepository: FcRepository) : ViewModel() {

    fun getMealsForDate(date: Long) = fcRepository.getMealsForDay(date)
}
