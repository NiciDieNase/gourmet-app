package de.nicidienase.geniesser_app.ui.fccampus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import de.nicidienase.geniesser_app.GourmetViewModelFactory
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentFccampusBinding
import timber.log.Timber

class FcCampusMenuFragment : Fragment() {

    private val viewModel: FcViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

    init {
        lifecycle.addObserver(LifecycleLogger(FcCampusMenuFragment::class.java.simpleName))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFccampusBinding.inflate(inflater, container, false)

        val fcMealAdapter = FcMealAdapter()
        binding.mealList.apply {
            adapter = fcMealAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val date = arguments?.getString(KEY_DATE) ?: "2020-09-02"

        Timber.i("Date: $date")

        viewModel.meals.observe(viewLifecycleOwner, Observer {
            fcMealAdapter.submitList(it)
        })
        viewModel.setDate(date)

        return binding.root
    }

    companion object {
        private const val KEY_DATE = "date"

        fun menuFragmentForDate(date: String) = FcCampusMenuFragment().apply {
            val args = Bundle()
            args.putString(KEY_DATE, date)
            arguments = args
        }
    }
}
