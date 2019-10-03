package de.nicidienase.geniesser_app.ui.news

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
import de.nicidienase.geniesser_app.databinding.FragmentNewsBinding

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            setupActionBarWithNavController(findNavController())
        }

        viewModel =
            ViewModelProviders.of(this, GourmetViewModelFactory.getInstance(requireContext()))[NewsViewModel::class.java]

        val adapter = NewsListAdapter {
            findNavController().navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(it))
        }
        binding.newsList.adapter = adapter
        binding.newsList.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.getNews().observe(this, Observer {
            adapter.submitList(it)
        })
        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.update()
            }
            viewModel.updating.observe(this@NewsListFragment, Observer {
                isRefreshing = it
            })
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.seen()
    }
}