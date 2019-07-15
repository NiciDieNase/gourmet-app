package de.nicidienase.geniesser_app

import de.nicidienase.geniesser_app.data.api.SpeiseplanApi
import de.nicidienase.geniesser_app.data.api.buildMenuApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import android.os.AsyncTask.execute
import okhttp3.OkHttpClient
import okhttp3.Request


class ExampleUnitTest {

    lateinit var api: SpeiseplanApi

    @Before
    fun setup() {
        api = buildMenuApi()
    }

    @Test
    fun testGetMenu() {
        runBlocking {

            val locations = api.getLocations()

            assertTrue(locations.size > 1)
        }
    }

    @Test
    fun testRequest() {
        runBlocking {
            val menu = api.getMenu(3317)

            assertTrue(menu.size == 1)

        }
    }
}
