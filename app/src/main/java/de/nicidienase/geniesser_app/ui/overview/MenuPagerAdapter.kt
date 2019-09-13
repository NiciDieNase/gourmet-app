package de.nicidienase.geniesser_app.ui.overview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        val date = dates?.get(position)
        return if (date != null) SimpleDateFormat("EEE, dd. MMMM", Locale.getDefault()).format(date)
        else "NoDate"
    }
}
