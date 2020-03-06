package de.nicidienase.geniesser_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Additive::class, Allergen::class, Dish::class, Location::class, Property::class, News::class],
    version = 6
)
@TypeConverters(FoodConverters::class)
abstract class GourmetDatabase : RoomDatabase() {

    abstract fun getAdditiveDao(): AdditiveDao
    abstract fun getAllergenDao(): AllergenDao
    abstract fun getDishDao(): DishDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getPropertyDao(): PropertyDao
    abstract fun getNewsDao(): NewsDao

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, GourmetDatabase::class.java, "food_database")
                .addMigrations(MIGRATION_5_6)
                .fallbackToDestructiveMigration()
                .build()

        internal val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE News ADD COLUMN active INTEGER NOT NULL DEFAULT 1")
            }
        }
    }
}