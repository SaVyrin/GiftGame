package ru.savyrin.gift.ui.finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentFinishBinding
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class FinishFragment : Fragment() {

    @Inject
    @Named("single")
    lateinit var textChangeAnimation: AlphaAnimation

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
                    FinishScreenState.ANIMATING -> {
                        binding.blockScreen.isVisible = true
                    }
                    FinishScreenState.SHOWING_GREETINGS -> {
                        binding.blockScreen.isVisible = false
                    }
                    FinishScreenState.DONE -> {
                        findNavController().navigate(R.id.action_finishFragment_to_startFragment)
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