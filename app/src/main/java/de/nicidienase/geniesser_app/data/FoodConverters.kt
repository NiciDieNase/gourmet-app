package de.nicidienase.geniesser_app.data

import androidx.room.TypeConverter

class FoodConverters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun intListToString(ints: List<Int>?): String {
            return ints?.map { it.toString() }?.reduce { acc, s -> "$acc,$s" } ?: ""
        }

        @TypeConverter
        @JvmStatic
        fun stringToIntList(string: String): List<Int> {
            return if(string.isNullOrEmpty()) emptyList()
            else string.split(",").map { it.toInt() }
        }
    }
}