package de.nicidienase.geniesser_app.data

import androidx.room.TypeConverter
import java.util.Date

object FoodConverters {

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<Int>?): String {
        return if (ints == null || ints.isEmpty()) {
            ""
        } else {
            ints.map { it.toString() }.reduce { acc, s -> "$acc,$s" }
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToIntList(string: String): List<Int> {
        return if (string.isNullOrEmpty()) emptyList()
        else string.split(",").map { it.toInt() }
    }

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestampe(date: Date?): Long? = date?.time
}
