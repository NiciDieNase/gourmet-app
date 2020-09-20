package de.nicidienase.geniesser_app.ui.fccampus

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import de.nicidienase.geniesser_app.util.CalendarUtils
import de.nicidienase.geniesser_app.util.DateListDiffUtil
import java.util.Date

class FcOverviewPagerAdapter(fragment: Fragment, var dates: List<Date>) :
    FragmentStateAdapter(fragment) {

    fun submitItems(dates: List<Date>) {
        val diffResult = DiffUtil.calculateDiff(DateListDiffUtil(this.dates, dates))
        this.dates = dates
        diffResult.dispatchUpdatesTo(this)
    }

    override fun createFragment(position: Int) =
        FcMenuFragment.menuFragmentForDate(dates[position])

    override fun getItemCount() = dates.size

    fun getPageTitle(position: Int): CharSequence? {
        return CalendarUtils.formatDateForPager(dates[position])
    }
}
