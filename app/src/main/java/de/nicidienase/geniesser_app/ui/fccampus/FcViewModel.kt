package de.nicidienase.geniesser_app.ui.fccampus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.nicidienase.geniesser_app.api.fccampus.FcCampusApi
import de.nicidienase.geniesser_app.api.fccampus.MealDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FcViewModel(private val fcApi: FcCampusApi) : ViewModel() {

    private val _meals: MutableLiveData<List<MealDto>> = MutableLiveData()
    val meals: LiveData<List<MealDto>> = _meals

    fun setDate(date: String) = viewModelScope.launch(Dispatchers.IO) {
        val menus = fcApi.getMenus(date, date)
        _meals.postValue(menus.dayMenus[0].meals)
    }
}
