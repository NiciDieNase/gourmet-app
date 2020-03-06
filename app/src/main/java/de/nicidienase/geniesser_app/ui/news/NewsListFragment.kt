package de.nicidienase.geniesser_app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentNewsBinding

class NewsListFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel

    init {
        lifecycle.addObserver(LifecycleLogger(NewsDetailFragment::class.java.simpleName))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_select_location -> {
                findNavController().navigate(R.id.locationSelectFragment)
                true
            }
            R.id.action_cleanup_news -> {
                viewModel.cleanup()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.seen()
    }
}