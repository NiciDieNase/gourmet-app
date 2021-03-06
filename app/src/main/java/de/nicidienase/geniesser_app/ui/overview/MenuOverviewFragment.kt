package de.nicidienase.geniesser_app.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentMealOverviewBinding
import de.nicidienase.geniesser_app.util.CalendarUtils

class MenuOverviewFragment : Fragment() {

    private lateinit var pagerAdapter: MenuPagerAdapter
    private lateinit var binding: FragmentMealOverviewBinding
    private val viewModel: MenuViewModel by viewModels {
        GourmetViewModelFactory.getInstance(
            requireContext()
        )
    }

    init {
        lifecycle.addObserver(LifecycleLogger(MenuOverviewFragment::class.java.simpleName))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealOverviewBinding.inflate(inflater, container, false)
        viewModel.updateDishes()

        pagerAdapter = MenuPagerAdapter(this, emptyList())
        binding.pager.adapter = pagerAdapter
        binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

        viewModel.getAvailableDays().observe(
            viewLifecycleOwner,
            Observer { dates ->
                pagerAdapter.submitItems(dates)
//            viewModel.selectedDay?.let {
//                if (dates.contains(it)) {
//                    binding.pager.setCurrentItem(dates.indexOf(it), false)
//                }
//            }
            }
        )
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.selectedDay = pagerAdapter.dates[position]
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        pagerAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_goto_today -> {
                val index =
                    if (viewModel.hideOldMenu) {
                        0
                    } else {
                        CalendarUtils.getIndexOfDay(pagerAdapter.dates)
                    }
                binding.pager.setCurrentItem(index, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = MenuOverviewFragment::class.java.simpleName
        private const val SELECTED_DATE = "selected_date"
    }
}
