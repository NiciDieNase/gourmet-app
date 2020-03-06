package de.nicidienase.geniesser_app

import de.nicidienase.geniesser_app.api.FeedbackMessageItem
import de.nicidienase.geniesser_app.api.GourmetApi
import de.nicidienase.geniesser_app.data.Additive
import de.nicidienase.geniesser_app.data.Allergen
import de.nicidienase.geniesser_app.data.Category
import de.nicidienase.geniesser_app.data.Location
import de.nicidienase.geniesser_app.data.News
import de.nicidienase.geniesser_app.data.Property
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ApiUnitTest {

    lateinit var api: GourmetApi

    @Before
    fun setup() {
        api = GourmetApi.instance
    }

    @Test
    fun locations() = runBlocking {
        val locations = api.getLocations()
        assertTrue(locations.isNotEmpty())

        val list = locations.map { Location.fromDto(it) }
        assertEquals(locations.size, list.size)
    }

    @Test
    fun menu() = runBlocking {
        val menu = api.getMenu(DEFAULT_LOCATION)
        assertTrue(menu?.size != 0)
    }

    @Test
    fun categories() = runBlocking {
        val categories = api.getMenuCategories(DEFAULT_LOCATION)
        assertTrue(categories.isNotEmpty())

        val list = categories.map { Category.fromDto(it) }
        assertEquals(categories.size, list.size)
    }

    @Test
    fun properties() = runBlocking {
        val properties = api.getDishProperties(DEFAULT_LOCATION)
        assertTrue(properties.isNotEmpty())

        val list = properties.map { Property.fromDto(it) }
        assertEquals(properties.size, list.size)
    }

    @Test
    fun additives() = runBlocking {
        val additives = api.getAdditives(DEFAULT_LOCATION)
        assertTrue(additives.isNotEmpty())

        val list = additives.map { Additive.fromDto(it) }
        assertEquals(additives.size, list.size)
    }

    @Test
    fun allergens() = runBlocking {
        val allergens = api.getAllergens(DEFAULT_LOCATION)
        assertTrue(allergens.isNotEmpty())

        val list = allergens.map { Allergen.fromAllergenDto(it) }
        assertEquals(allergens.size, list.size)
    }

    @Test
    fun news() = runBlocking {
        val news = api.getNews(DEFAULT_LOCATION)
        assertTrue(news.isNotEmpty())

        val list = news.map { News.fromNewsDto(it, DEFAULT_LOCATION) }
        assertEquals(news.size, list.size)
    }

    @Test
    fun feedbackCategories() = runBlocking {
        val feedbackCategories = api.getFeedbackCategories()
        assertTrue(feedbackCategories.isNotEmpty())
    }

    @Test
    fun feedbackMessage() {
        val list = listOf(
            FeedbackMessageItem(1,1,1,"Foo", "2020-01-01"),
            FeedbackMessageItem(1,1,1,1, "2020-01-01")
        )
        assertEquals(2, list.size)
    }

    companion object {
        const val DEFAULT_LOCATION = 3317L
    }
}
