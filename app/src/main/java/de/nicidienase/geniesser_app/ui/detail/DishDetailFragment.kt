package de.nicidienase.geniesser_app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.databinding.FragmentDishDetailBinding

class DishDetailFragment : Fragment() {

    val args: DishDetailFragmentArgs by navArgs()

    init {
        lifecycle.addObserver(LifecycleLogger(DishDetailFragment::class.java.simpleName))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDishDetailBinding.inflate(inflater, container, false)
        binding.dish = args.dish
        return binding.root
    }
}