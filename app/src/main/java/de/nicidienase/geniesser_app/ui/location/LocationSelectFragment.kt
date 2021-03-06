package de.nicidienase.geniesser_app.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentSelectLocationsBinding

class LocationSelectFragment : Fragment() {

    init {
        lifecycle.addObserver(LifecycleLogger(LocationSelectFragment::class.java.simpleName))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectLocationsBinding.inflate(inflater, container, false)

        val viewModel: LocationViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

        val adapter = LocationListAdapter {
            viewModel.selectLocation(it)
            findNavController().popBackStack()
        }
        binding.locationList.adapter = adapter
        binding.locationList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.getLocations().observe(
            viewLifecycleOwner,
            Observer {
                adapter.submitList(it)
            }
        )

        return binding.root
    }
}
