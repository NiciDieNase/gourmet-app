package de.nicidienase.geniesser_app.ui.fccampus

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter

class FcOverviewPagerAdapter(fragment: Fragment, var dates: List<String>) :
    FragmentStateAdapter(fragment) {

    fun submitItems(dates: List<String>) {
        val diffResult = DiffUtil.calculateDiff(StringDiffUtil(dates))
        this.dates = dates
        diffResult.dispatchUpdatesTo(this)
    }

    override fun createFragment(position: Int) =
        FcCampusMenuFragment.menuFragmentForDate(dates[position])

    override fun getItemCount() = dates.size

    fun getPageTitle(position: Int): CharSequence? {
        return dates[position]
    }

    inner class StringDiffUtil(private val newItems: List<String>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return dates[oldItemPosition] == newItems[newItemPosition]
        }

        override fun getOldListSize(): Int = dates.size

        override fun getNewListSize(): Int = newItems.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItemPosition, newItemPosition)
    }
}
