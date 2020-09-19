package de.nicidienase.geniesser_app

import de.nicidienase.geniesser_app.api.fccampus.FcCampusApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FcCampusApiTest {

    lateinit var api: FcCampusApi

    @BeforeEach
    fun setup() {
        api = FcCampusApi.instance
    }

    @Test
    fun getMealTimes() = runBlocking {
        val mealTimes = api.getMealTimes(38)
        println(mealTimes)
        val first = mealTimes.mealTimes.first { it.calendarWeek == 38 }
        assertNotNull(first)
    }

    @Test
    fun getMenus() = runBlocking {
        val date = "2020-09-02"
        val menus = api.getMenus(date, date)
        println(menus)
        val first = menus.dayMenus.first { it.day.startsWith(date) }
        assertNotNull(first)
    }
}