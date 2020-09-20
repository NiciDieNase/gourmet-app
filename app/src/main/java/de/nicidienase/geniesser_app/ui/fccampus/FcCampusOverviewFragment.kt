package de.nicidienase.geniesser_app.ui.fccampus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentFcOverviewBinding

class FcCampusOverviewFragment : Fragment() {

    private lateinit var pagerAdapter: FcOverviewPagerAdapter

    init {
        lifecycle.addObserver(LifecycleLogger(FcCampusOverviewFragment::class.java.simpleName))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFcOverviewBinding.inflate(inflater, container, false)

        pagerAdapter = FcOverviewPagerAdapter(
            this,
            listOf("2020-09-14", "2020-09-15", "2020-09-16", "2020-09-17", "2020-09-18")
        )
        binding.fcPager.adapter = pagerAdapter
        binding.fcTabs.tabMode = TabLayout.MODE_SCROLLABLE

        TabLayoutMediator(binding.fcTabs, binding.fcPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        pagerAdapter.notifyDataSetChanged()
    }
}
