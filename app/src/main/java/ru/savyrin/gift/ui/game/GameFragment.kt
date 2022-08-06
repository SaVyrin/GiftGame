package ru.savyrin.gift.ui.game

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentGameBinding
import javax.inject.Inject

@AndroidEntryPoint
class GameFragment : Fragment() {

    @Inject
    lateinit var valueAnimator: ValueAnimator

    private val viewModel: GameViewModel by viewModels()

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeGameState()
        setPresentClickListener()
        setLeftButtonClickListener()
        setRightButtonClickListener()
        setUpButtonClickListener()
        setDownButtonClickListener()
    }

    private fun observeGameState() {
        viewModel.gameState.observe(viewLifecycleOwner) { gameState ->
            gameState?.let {
                when (gameState) {
                    GameState.NOT_STARTED -> {
                        viewModel.startGame()
                        setPresentHealth()
                    }
                    GameState.FINISHED -> {
                        findNavController().navigate(R.id.action_gameFragment_to_finishFragment)
                    }
                    GameState.NOT_FINISHED -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    private fun setPresentHealth() {
        val presentHealth = viewModel.getPresentHealth()
        binding.presentHealthTv.text = presentHealth.toString()
    }

    private fun setPresentClickListener() {
        binding.presentImage.setOnClickListener {
            viewModel.beatPresent()
            setPresentHealth()
            startPresentBeatAnimation()
        }
    }

    private fun setLeftButtonClickListener() {
        binding.leftArrowBtn.setOnClickListener {
            viewModel.leftButtonClick()
        }
    }

    private fun setRightButtonClickListener() {
        binding.rightArrowBtn.setOnClickListener {
            viewModel.rightButtonClick()
        }
    }

    private fun setUpButtonClickListener() {
        binding.upArrowBtn.setOnClickListener {
            viewModel.upButtonClick()
        }
    }

    private fun setDownButtonClickListener() {
        binding.downArrowBtn.setOnClickListener {
            viewModel.downButtonClick()
        }
    }

    private fun startPresentBeatAnimation() {
        valueAnimator.apply {
            start()

            addUpdateListener { updatedAnimation ->
                binding.presentImage.scaleX = updatedAnimation.animatedValue as Float
                binding.presentImage.scaleY = updatedAnimation.animatedValue as Float
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}