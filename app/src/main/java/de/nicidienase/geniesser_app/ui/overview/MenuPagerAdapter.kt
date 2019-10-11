package de.nicidienase.geniesser_app.ui.overview

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MenuPagerAdapter(fragment: Fragment, var dates: List<Date>) : FragmentStateAdapter(fragment) {

    fun submitItems(dates: List<Date>) {
        val diffResult = DiffUtil.calculateDiff(DateListDiffUtil(this.dates, dates))
        this.dates = dates
        diffResult.dispatchUpdatesTo(this)
    }

    override fun createFragment(position: Int) = MenuFragment.menuFragmentForDate(dates[position])

    override fun getItemCount() = dates.size

    fun getPageTitle(position: Int): CharSequence? {
        val date = dates.get(position)
        return SimpleDateFormat("EEE, dd. MMMM", Locale.getDefault()).format(date)
    }
}
