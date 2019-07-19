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
abstract class FoodDatabase : RoomDatabase() {

    abstract fun getAdditiveDao(): AdditiveDao
    abstract fun getAllergenDao(): AllergenDao
    abstract fun getDishDao(): DishDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getPropertyDao(): PropertyDao

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, FoodDatabase::class.java, "food_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}