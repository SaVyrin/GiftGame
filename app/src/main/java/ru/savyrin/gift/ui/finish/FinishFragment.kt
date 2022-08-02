package ru.savyrin.gift.ui.finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var viewModelFactory: FinishViewModelFactory
    private val viewModel: FinishViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentFinishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinishBinding.inflate(inflater, container, false)
        getViewModelFactory()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeScreenState()
        showNextGreetingText()
        setScreenClickListener()
    }

    private fun getViewModelFactory() {
        val textToShow = resources.getStringArray(R.array.finish_screen_greetings_text)
        viewModelFactory = FinishViewModelFactory(textToShow)
    }

    private fun observeScreenState() {
        viewModel.finishScreenState.observe(viewLifecycleOwner) { screenState ->
            screenState?.let {
                when (screenState) {
                    FinishScreenState.DONE -> {
                        findNavController().navigate(R.id.action_finishFragment_to_startFragment)
                    }
                    FinishScreenState.SHOWING_GREETINGS -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    private fun showNextGreetingText() {
        val nextGreetingText = viewModel.getNextGreeting()
        animateTextChange(nextGreetingText)
    }

    private fun animateTextChange(nextGreetingText: String) {
        // TODO добавить в di
        val textChangeAnimation = AlphaAnimation(1.0f, 0.0f)
        textChangeAnimation.duration = 500
        textChangeAnimation.repeatCount = 1
        textChangeAnimation.repeatMode = Animation.REVERSE

        textChangeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {
                binding.greetingsTv.text = nextGreetingText
            }
        })
        binding.greetingsTv.startAnimation(textChangeAnimation)
    }

    private fun setScreenClickListener() {
        binding.root.setOnClickListener {
            showNextGreetingText()
        }
    }
}