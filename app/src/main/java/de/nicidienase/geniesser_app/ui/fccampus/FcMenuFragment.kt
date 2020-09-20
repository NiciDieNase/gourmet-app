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
import java.util.Date
import timber.log.Timber

class FcMenuFragment : Fragment() {

    private val viewModel: FcMenuViewModel by viewModels { GourmetViewModelFactory.getInstance(requireContext()) }

    init {
        lifecycle.addObserver(LifecycleLogger(FcMenuFragment::class.java.simpleName))
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

        val date = arguments?.getLong(KEY_DATE) ?: 1563487200000

        Timber.i("Date: $date")

        viewModel.getMealsForDate(date).observe(viewLifecycleOwner, Observer {
            fcMealAdapter.submitList(it)
        })

        return binding.root
    }

    companion object {
        private const val KEY_DATE = "date"

        fun menuFragmentForDate(date: Date) = FcMenuFragment().apply {
            val args = Bundle()
            args.putLong(KEY_DATE, date.time)
            arguments = args
        }
    }
}
