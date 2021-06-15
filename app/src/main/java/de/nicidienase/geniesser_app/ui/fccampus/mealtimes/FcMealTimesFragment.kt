package de.nicidienase.geniesser_app.ui.fccampus.mealtimes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.data.fccampus.MealTime
import de.nicidienase.geniesser_app.util.CalendarUtils

class FcMealTimesFragment : Fragment() {

    private val viewModel: MealTimesViewModel by viewModels {
        GourmetViewModelFactory.getInstance(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.updateMealTimes()
        return ComposeView(requireContext()).apply {
            setContent {
                val mealTimeList: List<MealTime> by viewModel.getMealTimes()
                    .observeAsState(emptyList())
                MealTimesList(list = mealTimeList)
            }
        }
    }
}

@Composable
fun MealTimesList(list: List<MealTime>) {
    MaterialTheme {
        LazyColumn() {
            val groupBy = list.groupBy { it.calendarWeek }
            for (week in groupBy) {
                item {
                    WeekHeading(week.key)
                }
                items(week.value) { item ->
                    MealTimesItem(times = item)
                }
                item {
                    Divider(
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun WeekHeading(week: Int, modifier: Modifier = Modifier) {
    Text(
        text = "Woche $week",
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun MealTimesItem(times: MealTime) {
    Column {
        Text(
            text = times.description,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = "${CalendarUtils.formatTimeOnly(times.from)} - ${CalendarUtils.formatTimeOnly(times.to)}",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "MealTimes"
)
@Composable
fun MealTimesTest() {
    MealTimesList(list = getDummyList())
}

@Composable
private fun getDummyList(): List<MealTime> {
    val time1 = CalendarUtils.parseDateString("2020-01-01T11:45:00.000Z")!!
    val time2 = CalendarUtils.parseDateString("2020-01-01T12:30:00.000Z")!!
    val time3 = CalendarUtils.parseDateString("2020-01-01T13:15:00.000Z")!!
    val mealTimeDto1 = MealTime(42, "Cube X", time1, time2)
    val mealTimeDto2 = MealTime(42, "Cube Y", time2, time3)
    val mealTimeDto3 = MealTime(43, "Cube X", time1, time2)
    val mealTimeDto4 = MealTime(43, "Cube Y", time2, time3)
    return listOf(mealTimeDto1, mealTimeDto2, mealTimeDto3, mealTimeDto4)
}
