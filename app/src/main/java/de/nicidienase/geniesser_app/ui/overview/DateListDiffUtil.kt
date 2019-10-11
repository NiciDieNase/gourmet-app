package de.nicidienase.geniesser_app.ui.overview

import androidx.recyclerview.widget.DiffUtil
import java.util.Date

class DateListDiffUtil(private val oldDates: List<Date>, private val newDates: List<Date>?) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDates[oldItemPosition] == newDates?.get(newItemPosition)
    }

    override fun getOldListSize() = oldDates.size

    override fun getNewListSize() = newDates?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDates[oldItemPosition].time == newDates?.get(newItemPosition)?.time
    }
}
