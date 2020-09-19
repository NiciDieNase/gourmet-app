package de.nicidienase.geniesser_app.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentMenuBinding
import java.util.Date
import timber.log.Timber

class MenuListFragment : Fragment() {

    init {
        lifecycle.addObserver(LifecycleLogger(MenuListFragment::class.java.simpleName))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        val viewModel =
            ViewModelProviders.of(
                this,
                GourmetViewModelFactory.getInstance(requireContext())
            ).get(MenuViewModel::class.java)

        val day = arguments?.getLong(KEY_DAY) ?: 1563487200000

        Timber.i("Day: %s", Date(day))

        val dishAdapter = DishAdapter { dish, binding ->
            findNavController().navigate(
                MenuOverviewFragmentDirections.actionMealOverviewFragmentToDishDetailFragment(
                    dish,
                    dish.category?.categoryName ?: "Detail"),
                FragmentNavigatorExtras(
                    binding.nameText to binding.nameText.transitionName,
                    binding.priceText to binding.priceText.transitionName
                )
            )
        }
        viewModel.getDishesForDay(day).observe(viewLifecycleOwner, Observer {
            dishAdapter.submitList(it)
        })

        binding.menuList.apply {
            adapter = dishAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        viewModel.isRefreshing.observe(viewLifecycleOwner, Observer {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateDishes()
        }

        return binding.root
    }

    companion object {
        private const val KEY_DAY = "day"

        fun menuFragmentForDate(date: Date) = MenuListFragment().apply {
            val args = Bundle()
            args.putLong(KEY_DAY, date.time)
            arguments = args
        }
    }
}
