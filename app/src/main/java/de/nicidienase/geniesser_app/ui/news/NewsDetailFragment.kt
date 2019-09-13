package de.nicidienase.geniesser_app.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import de.nicidienase.geniesser_app.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {

    val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
//        (activity as AppCompatActivity).apply {
//            setSupportActionBar(binding.toolbar)
//            setupActionBarWithNavController(findNavController())
//        }
        binding.item = args.item

        return binding.root
    }
}