package de.nicidienase.geniesser_app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import de.nicidienase.geniesser_app.BuildConfig
import de.nicidienase.geniesser_app.LifecycleLogger
import de.nicidienase.geniesser_app.R
import de.nicidienase.geniesser_app.databinding.FragmentDishDetailBinding

class DishDetailFragment : Fragment() {

    private val args: DishDetailFragmentArgs by navArgs()

    init {
        lifecycle.addObserver(LifecycleLogger(DishDetailFragment::class.java.simpleName))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        postponeEnterTransition()
        val binding = FragmentDishDetailBinding.inflate(inflater, container, false)
        binding.dish = this.args.dish
        if (BuildConfig.FLAVOR == "dev") {
            binding.debugDishText.visibility = View.VISIBLE
        }
        val sharedElementTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
        sharedElementEnterTransition = sharedElementTransition
        sharedElementReturnTransition = sharedElementTransition
        enterTransition = Fade()
        exitTransition = Fade()
        binding.root.doOnPreDraw {
            startPostponedEnterTransition()
        }
        return binding.root
    }
}
