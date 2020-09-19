package de.nicidienase.geniesser_app.data

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import java.io.IOException
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
            InstrumentationRegistry.getInstrumentation(),
            GourmetDatabase::class.java.canonicalName,
            FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate5to6() {
        var db = helper.createDatabase(TEST_DB, 5)
        db.apply {
            execSQL("INSERT INTO News (title, date, content, internal, imageUrl, locationId, backendId, newNews) " +
                    "              VALUES ('test',3600, 'test',  0,        '',       1,          1,         1)")
            close()
        }
        db = helper.runMigrationsAndValidate(TEST_DB, 6, true, GourmetDatabase.MIGRATION_5_6)
        val cursor = db.query("SELECT * FROM News")

        assertEquals(10, cursor.columnCount)
        assertTrue(cursor.columnNames.contains("active"))
    }
}
