package de.nicidienase.geniesser_app.ui.fccampus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentFcOverviewBinding

class FcOverviewFragment : Fragment() {

    private lateinit var pagerAdapter: FcOverviewPagerAdapter
    private val viewModel: FcOverviewViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

    init {
        lifecycle.addObserver(LifecycleLogger(FcOverviewFragment::class.java.simpleName))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFcOverviewBinding.inflate(inflater, container, false)

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

        // Setup Observer
        viewModel.availableDays.observe(viewLifecycleOwner, Observer {
            pagerAdapter.submitItems(it)
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        pagerAdapter.notifyDataSetChanged()
        viewModel.updateMenu()
    }
}
