package de.nicidienase.geniesser_app.ui.fccampus

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
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentFcOverviewBinding
import de.nicidienase.geniesser_app.util.CalendarUtils

class FcOverviewFragment : Fragment() {

    private lateinit var pagerAdapter: FcOverviewPagerAdapter
    private lateinit var binding: FragmentFcOverviewBinding
    private val viewModel: FcOverviewViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

    init {
        lifecycle.addObserver(LifecycleLogger(FcOverviewFragment::class.java.simpleName))
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
        binding = FragmentFcOverviewBinding.inflate(inflater, container, false)

        // Setup Pager
        pagerAdapter = FcOverviewPagerAdapter(
            this,
            emptyList()
        )
        binding.fcPager.adapter = pagerAdapter
        binding.fcTabs.tabMode = TabLayout.MODE_SCROLLABLE

        TabLayoutMediator(binding.fcTabs, binding.fcPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

        binding.fcPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(pagerAdapter.dates.size < position){
                    viewModel.selectedDay = pagerAdapter.dates[position]
                }
            }
        })

        // Setup Observer
        viewModel.availableDays.observe(viewLifecycleOwner, Observer {
            pagerAdapter.submitItems(it)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu_fc, menu)
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
                binding.fcPager.setCurrentItem(index, true)
                true
            }
            R.id.action_info -> {
                findNavController().navigate(FcOverviewFragmentDirections.actionFcCampusOverviewFragmentToFcMealTimesFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        pagerAdapter.notifyDataSetChanged()
        viewModel.updateMenu()
    }
}
