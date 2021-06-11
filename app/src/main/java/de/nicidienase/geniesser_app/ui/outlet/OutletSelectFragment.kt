package de.nicidienase.geniesser_app.ui.outlet

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
import de.nicidienase.geniesser_app.ui.location.LocationViewModel

class OutletSelectFragment : Fragment() {

    init {
        lifecycle.addObserver(LifecycleLogger(OutletSelectFragment::class.java.simpleName))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectLocationsBinding.inflate(inflater, container, false)

        val viewModel: LocationViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

        val adapter = OutletListAdapter {
            viewModel.selectOutlet(it)
            findNavController().popBackStack()
        }

        binding.locationList.apply {
            this.adapter = adapter
            layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }

        viewModel.getOutlets().observe(
            viewLifecycleOwner,
            Observer {
                adapter.submitList(it)
            }
        )
        return binding.root
    }
}
