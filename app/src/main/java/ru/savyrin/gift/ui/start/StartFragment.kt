package ru.savyrin.gift.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentStartBinding
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class StartFragment : Fragment() {

    @Inject
    @Named("infinite")
    lateinit var textAnimation: AlphaAnimation

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenClickListener()
        startTextFadingAnimation()
    }

    private fun setScreenClickListener() {
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
    }

    private fun startTextFadingAnimation() {
        binding.hintTv.startAnimation(textAnimation)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}