package de.nicidienase.geniesser_app.overview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.text.SimpleDateFormat
import java.util.*

class MenuPagerAdapter(fragmentManager: FragmentManager, var dates: List<Date>?) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return dates?.get(position)?.let {
            MenuFragment.menuFragmentForDate(
                it
            )
        } ?: Fragment()
    }

    override fun getCount() = dates?.size ?: 0

    override fun getPageTitle(position: Int): CharSequence? {
        return SimpleDateFormat("EEE, dd. MMMM", Locale.getDefault()).format(dates?.get(position))
    }
}
