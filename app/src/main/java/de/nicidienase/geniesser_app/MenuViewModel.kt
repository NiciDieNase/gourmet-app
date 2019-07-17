package de.nicidienase.geniesser_app

import androidx.lifecycle.ViewModel
import de.nicidienase.geniesser_app.data.MenuRepository

class MenuViewModel(val menuRepository: MenuRepository): ViewModel() {

    fun getDishes() = menuRepository.getDishes()

    fun updateDishes() {
        menuRepository.update()
    }
}