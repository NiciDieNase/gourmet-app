package de.nicidienase.geniesser_app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import de.nicidienase.geniesser_app.databinding.FragmentDishDetailBinding

class DishDetailFragment : Fragment() {

    val args: DishDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDishDetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            setupActionBarWithNavController(findNavController())
        }

        binding.dish = args.dish
        return binding.root
    }
}