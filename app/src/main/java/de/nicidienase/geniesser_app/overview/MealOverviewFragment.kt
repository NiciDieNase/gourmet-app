package de.nicidienase.geniesser_app.overview

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
import com.google.android.material.tabs.TabLayout
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentMealOverviewBinding

class MealOverviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMealOverviewBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        val viewModel =
            ViewModelProviders.of(this,
                GourmetViewModelFactory(requireContext())
            ).get(MenuViewModel::class.java)

        viewModel.updateDishes()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val pagerAdapter = MenuPagerAdapter(childFragmentManager, emptyList())
        binding.pager.adapter = pagerAdapter
        binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        binding.tabs.setupWithViewPager(binding.pager)

        viewModel.getAvailableDays().observe(this, Observer {
            pagerAdapter.dates = it
            pagerAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.overview_menu, menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_select_location -> {
                findNavController().navigate(MealOverviewFragmentDirections.actionMealOverviewFragmentToLocationSelectFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private val TAG = MealOverviewFragment::class.java.simpleName
    }
}