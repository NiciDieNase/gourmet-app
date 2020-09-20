package de.nicidienase.geniesser_app

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import de.nicidienase.geniesser_app.databinding.ActivityGourmetBinding

class GourmetActivity : AppCompatActivity() {

    private val viewModel: GourmetActivityViewModel by viewModels {
        GourmetViewModelFactory.getInstance(
            this
        )
    }

    init {
        lifecycle.addObserver(LifecycleLogger(GourmetActivity::class.java.simpleName))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityGourmetBinding>(this, R.layout.activity_gourmet)

        val navController = findNavController(R.id.nav_host)
        binding.bottomNavigationView.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mealOverviewFragment,
                R.id.fcCampusOverviewFragment,
                R.id.newsListFragment,
                R.id.prefFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        viewModel.newsCount.observe(this, Observer {
            if (it == 0) {
                binding.bottomNavigationView.removeBadge(R.id.newsListFragment)
            } else {
                binding.bottomNavigationView.getOrCreateBadge(R.id.newsListFragment).number = it
            }
        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (noToolbarIds.contains(destination.id)) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
            if (noBottomNavIds.contains(destination.id)) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
        viewModel.update()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.nav_host).navigateUp()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

    companion object {
        private val noToolbarIds = listOf(R.id.newsDetailFragment)
        private val noBottomNavIds = listOf(
            R.id.newsDetailFragment,
            R.id.locationSelectFragment
        )
    }
}
