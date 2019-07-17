package de.nicidienase.geniesser_app

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.nicidienase.geniesser_app.databinding.FragmentMealOverviewBinding

class MealOverviewFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMealOverviewBinding.inflate(inflater, container, false)

        val viewModel =
            ViewModelProviders.of(this, GourmetViewModelFactory(requireContext())).get(MenuViewModel::class.java)

        viewModel.updateDishes()

        activity?.setActionBar( binding.toolbar )

        setHasOptionsMenu(true)

        val pagerAdapter = MenuPagerAdapter(childFragmentManager, emptyList())
        binding.pager.adapter = pagerAdapter

        binding.tabs.setupWithViewPager(binding.pager)

        viewModel.getAvailableDays().observe(this, Observer {
            pagerAdapter.dates = it
            pagerAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_select_location -> {
                // TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}