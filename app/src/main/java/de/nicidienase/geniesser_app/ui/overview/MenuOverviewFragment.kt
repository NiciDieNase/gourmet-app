package de.nicidienase.geniesser_app.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.tabs.TabLayout
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentMealOverviewBinding
import java.util.Date

class MenuOverviewFragment : Fragment() {

    private lateinit var pagerAdapter: MenuPagerAdapter
    private lateinit var binding: FragmentMealOverviewBinding
    private lateinit var viewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMealOverviewBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        viewModel =
            ViewModelProviders.of(
                this,
                GourmetViewModelFactory.getInstance(requireContext())
            ).get(MenuViewModel::class.java)

        viewModel.updateDishes()

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            setupActionBarWithNavController(findNavController())
        }

        pagerAdapter = MenuPagerAdapter(childFragmentManager, emptyList())
        binding.pager.adapter = pagerAdapter
        binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        binding.tabs.setupWithViewPager(binding.pager)

        viewModel.getAvailableDays().observe(this, Observer {
            pagerAdapter.dates = it
            pagerAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_select_location -> {
                findNavController().navigate(MenuOverviewFragmentDirections.actionMealOverviewFragmentToLocationSelectFragment())
                true
            }
            R.id.action_goto_today -> {
                pagerAdapter.dates?.let {
                    binding.pager.setCurrentItem(getIndexOfToday(it), true)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getIndexOfToday(dates: List<Date>): Int {
        val now = Date()
        try {
            val today = dates.last { it.before(now) }
            return dates.indexOf(today)
        } catch (ex: NoSuchElementException) {
            // all entries are in the future, show first item
            return 0
        }
    }

    companion object {
        private val TAG = MenuOverviewFragment::class.java.simpleName
    }
}