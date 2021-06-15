package de.nicidienase.geniesser_app.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Date

class FoodConvertersTest {

    @Nested
    inner class ListToStringTests : ConverterTest<List<Int>?, String>(FoodConverters::intListToString) {

        @Test
        fun emptyListTest() = convertAndCompare(emptyList(), "")

        @Test
        fun nullTest() = convertAndCompare(null, "")

        @Test
        fun simpleListTest() = convertAndCompare(listOf(1, 2, 3, 4), "1,2,3,4")
    }

    @Nested
    inner class StringToIntListTests : ConverterTest<String, List<Int>?>(FoodConverters::stringToIntList) {

        @Test
        fun emptyString() = convertAndCompare("", emptyList())

        @Test
        fun simpleList() = convertAndCompare("6,7,8,9", listOf(6, 7, 8, 9))
    }

    @Nested
    inner class DateToLongTest : ConverterTest<Date?, Long?>(FoodConverters::dateToTimestampe) {
        @Test
        fun nullReturnsNull() = convertAndCompare(null, null)
    }

    @Nested
    inner class LongToDateTest : ConverterTest<Long?, Date?>(FoodConverters::fromTimestamp) {
        @Test
        fun nullReturnsNull() = convertAndCompare(null, null)

        @Test
        fun zeroToDate() = convertAndCompare(0, Date(0))
    }

    abstract class ConverterTest<I, O> (val converter: (input: I) -> O) {
        fun convertAndCompare(input: I, expected: O) {
            assertEquals(expected, converter.invoke(input))
        }
    }
}
