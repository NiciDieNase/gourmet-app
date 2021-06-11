package de.nicidienase.geniesser_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import de.nicidienase.geniesser_app.data.fccampus.FcMeal
import de.nicidienase.geniesser_app.data.fccampus.FcMealDao
import de.nicidienase.geniesser_app.data.fccampus.MealTime
import de.nicidienase.geniesser_app.data.fccampus.MealTimeDao

@Database(
    entities = [
        Additive::class,
        Allergen::class,
        Dish::class,
        Location::class,
        Property::class,
        News::class,
        Outlet::class,
        FcMeal::class,
        MealTime::class
    ],
    version = 10
)
@TypeConverters(FoodConverters::class)
abstract class GourmetDatabase : RoomDatabase() {

    abstract fun getAdditiveDao(): AdditiveDao
    abstract fun getAllergenDao(): AllergenDao
    abstract fun getDishDao(): DishDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getPropertyDao(): PropertyDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getOutletDao(): OutletDao

    abstract fun getFcMealDao(): FcMealDao
    abstract fun getMealTimeDao(): MealTimeDao

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GourmetDatabase::class.java,
                "food_database"
            )
                .fallbackToDestructiveMigrationFrom(1, 2, 3, 4)
                .addMigrations(
                    MIGRATION_5_6,
                    MIGRATION_6_7,
                    MIGRATION_7_8,
                    MIGRATION_8_9,
                    MIGRATION_9_10
                )
                .build()

        internal val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE News ADD COLUMN active INTEGER NOT NULL DEFAULT 1")
            }
        }

        internal val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Dish ADD COLUMN active INTEGER NOT NULL DEFAULT 1")
            }
        }

        internal val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `FcMeal` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `category` TEXT NOT NULL, `price` INTEGER NOT NULL, `uuid` TEXT NOT NULL, `date` INTEGER NOT NULL, `apiId` TEXT NOT NULL)")
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_FcMeal_apiId` ON `FcMeal` (`apiId`)")
            }
        }

        internal val MIGRATION_8_9 = object : Migration(8, 9) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `Outlet` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `outletId` INTEGER NOT NULL, `name` TEXT NOT NULL, `order` INTEGER NOT NULL, `locationId` INTEGER NOT NULL)")
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_Outlet_outletId` ON `Outlet` (`outletId`)")
                database.execSQL("CREATE INDEX IF NOT EXISTS `index_Outlet_locationId` ON `Outlet` (`locationId`)")
            }
        }

        internal val MIGRATION_9_10 = object : Migration(9, 10) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `MealTime` (`calendarWeek` INTEGER NOT NULL, `description` TEXT NOT NULL, `from` INTEGER NOT NULL, `to` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_MealTime_apiId` ON `MealTime` (`apiId`)")
            }
        }
    }
}
