package de.nicidienase.geniesser_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        Additive::class,
        Allergen::class,
        Dish::class,
        Location::class,
        Property::class,
        News::class,
        FcMeal::class,
        Outlet::class
    ],
    version = 9
)
@TypeConverters(FoodConverters::class)
abstract class GourmetDatabase : RoomDatabase() {

    abstract fun getAdditiveDao(): AdditiveDao
    abstract fun getAllergenDao(): AllergenDao
    abstract fun getDishDao(): DishDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getPropertyDao(): PropertyDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getFcMealDao(): FcMealDao
    abstract fun getOutletDao(): OutletDao

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
                    MIGRATION_8_9
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
    }
}
