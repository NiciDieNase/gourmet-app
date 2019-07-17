package de.nicidienase.geniesser_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProviders.of(this, GourmetViewModelFactory(requireContext())).get(MenuViewModel::class.java)

        val dishAdapter = DishAdapter()
        viewModel.getDishes().observe(this, Observer {
            dishAdapter.submitList(it)
        })

        binding.menuList.apply {
            adapter = dishAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        viewModel.updateDishes()

        return binding.root
    }
}