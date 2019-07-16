package de.nicidienase.geniesser_app

import de.nicidienase.geniesser_app.data.api.MenuApi
import de.nicidienase.geniesser_app.data.api.buildMenuApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class ApiUnitTest {

    lateinit var api: MenuApi

    @Before
    fun setup() {
        api = buildMenuApi()
    }

    @Test
    fun locations() = runBlocking {
        val locations = api.getLocations()
        assertTrue(locations.isNotEmpty())
    }

    @Test
    fun menu() = runBlocking {
        val menu = api.getMenu(DEFAULT_LOCATION)
        assertTrue(menu.size == 1)
    }

    @Test
    fun categories() = runBlocking {
        val categories = api.getMenuCategories(DEFAULT_LOCATION)
        assertTrue(categories.isNotEmpty())
    }

    @Test
    fun properties() = runBlocking {
        val properties = api.getDishProperties(DEFAULT_LOCATION)
        assertTrue(properties.isNotEmpty())
    }

    @Test
    fun additives() = runBlocking {
        val aditives = api.getAdditives(DEFAULT_LOCATION)
        assertTrue(aditives.isNotEmpty())
    }

    @Test
    fun allergens() = runBlocking {
        val allergens = api.getMenuCategories(DEFAULT_LOCATION)
        assertTrue(allergens.isNotEmpty())
    }

    companion object {
        const val DEFAULT_LOCATION = 3317
    }
}
