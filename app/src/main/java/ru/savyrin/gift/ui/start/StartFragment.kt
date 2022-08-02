package ru.savyrin.gift.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.navigation.fragment.findNavController
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentStartBinding

class StartFragment : Fragment() {

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
        setTextFadingAnimation()
    }

    private fun setScreenClickListener() {
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_gameFragment)
        }
    }

    private fun setTextFadingAnimation() {
        // TODO перенести это в di
        val textAnimation = AlphaAnimation(1.0f, 0.2f)
        textAnimation.duration = 1800
        textAnimation.repeatCount = Animation.INFINITE
        textAnimation.repeatMode = Animation.REVERSE
        binding.hintTv.startAnimation(textAnimation)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}