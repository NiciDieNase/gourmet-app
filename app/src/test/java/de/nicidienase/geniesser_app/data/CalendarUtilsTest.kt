package de.nicidienase.geniesser_app.data

import de.nicidienase.geniesser_app.util.CalendarUtils
import java.util.Date
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CalendarUtilsTest {

    @ParameterizedTest
    @EnumSource
    fun expectedWeeks(case: TestCase) {
        val parsedDate = CalendarUtils.parseDateString(case.dateString)
        if (parsedDate != null) {
            val weekFromDate = CalendarUtils.getWeekNumberForDate(parsedDate)
            assertEquals(case.expectedWeek, weekFromDate)
        } else {
            fail()
        }
    }

    enum class TestCase(val dateString: String, val expectedWeek: Int) {
        Week1of2020("2020-01-01T00:00:00", 1),
        Week1of2021("2020-01-04T00:00:00", 1),
        Week38of2020("2020-09-19T00:01:00", 38)
//        Week53of2020("2021-01-01T02:00:00", 53),
//        Week2of2021("2021-01-11T02:00:00", 2)
    }

    @ParameterizedTest
    @EnumSource
    fun dateInFuture(case: TestCase2) {
        val dateForDaysInFuture = CalendarUtils.getDateForDaysInFuture(case.daysToAdd, case.date)
        assertEquals(case.expectedDate, dateForDaysInFuture)
    }

    enum class TestCase2(val date: Date, val daysToAdd: Int, val expectedDate: Date) {
        Case1(Date(1600606763000), 1, Date(1600646400000)),
        Zero(Date(0), 1, Date(86400000)),
        NegativeDays(Date(561126896000), -7, Date(560476800000))
    }
}
