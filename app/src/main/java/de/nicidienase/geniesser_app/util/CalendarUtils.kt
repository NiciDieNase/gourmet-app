package de.nicidienase.geniesser_app.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object CalendarUtils {

    fun getWeekNumberForDate(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }

    fun getDateForDaysInFuture(daysInFuture: Int, currentDate: Date = Date()): Date {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.time = currentDate
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.add(Calendar.DAY_OF_MONTH, daysInFuture)
        return calendar.time
    }

    fun parseDateString(dateString: String): Date? {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(dateString)
    }

    fun formatDateForFcAPI(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }

    fun formatDateForPager(date: Date): String {
        return SimpleDateFormat("EEE, dd. MMMM", Locale.getDefault()).format(date)
    }

    fun getIndexOfDay(dates: List<Date>, day: Date = Date()): Int {
        return try {
            val today = dates.last { it.before(Date()) }
            dates.indexOf(today)
        } catch (ex: NoSuchElementException) {
            // all entries are in the future, show first item
            0
        }
    }
}
