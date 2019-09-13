package de.nicidienase.geniesser_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Additive::class, Allergen::class, Dish::class, Location::class, Property::class],
    version = 2
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
                .fallbackToDestructiveMigration()
                .build()
    }
}