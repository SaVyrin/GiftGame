package ru.savyrin.gift.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.savyrin.gift.R
import ru.savyrin.gift.databinding.FragmentGameBinding

class GameFragment : Fragment() {

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
    }

    private fun observeGameState() {
        viewModel.gameState.observe(viewLifecycleOwner) { gameState ->
            gameState?.let {
                when (gameState) {
                    GameState.NOT_STARTED -> {
                        viewModel.startGame()
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

    private fun setPresentClickListener() {
        binding.presentImage.setOnClickListener {
            viewModel.beatPresent()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}