package de.nicidienase.geniesser_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.nicidienase.geniesser_app.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.menuList.apply {
            adapter = DishAdapter()
        }

        return binding.root
    }
}