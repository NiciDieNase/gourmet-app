package de.nicidienase.geniesser_app.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentSelectLocationsBinding

class LocationSelectFragment : Fragment() {

    init {
        lifecycle.addObserver(LifecycleLogger(LocationSelectFragment::class.java.simpleName))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSelectLocationsBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            setupActionBarWithNavController(findNavController())
        }

        val viewModel =
            ViewModelProviders.of(this, GourmetViewModelFactory.getInstance(requireContext()))[LocationViewModel::class.java]

        val adapter = LocationListAdapter {
            viewModel.selectLocation(it)
            findNavController().popBackStack()
        }
        binding.locationList.adapter = adapter
        binding.locationList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.getLocations().observe(this, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}