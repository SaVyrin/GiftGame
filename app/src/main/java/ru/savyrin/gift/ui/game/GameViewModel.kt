package ru.savyrin.gift.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val mutableGameState = MutableLiveData(GameState.NOT_STARTED)
    val gameState: LiveData<GameState> = mutableGameState

    private val mutableCurrentPresent = MutableLiveData(PresentTypes.FIRST_PRESENT)
    val currentPresent: LiveData<PresentTypes> = mutableCurrentPresent

    private val game = Game()

    fun startGame() {
        game.start()
    }

    fun getPresentHealth(): Long {
        return game.getPresentHealth()
    }

    fun beatPresent() {
        val isPresentBeaten = game.beatPresent()
        if (isPresentBeaten) {
            when (mutableCurrentPresent.value) {
                PresentTypes.FIRST_PRESENT -> {
                    mutableCurrentPresent.value = PresentTypes.SECOND_PRESENT
                }
                PresentTypes.SECOND_PRESENT -> {
                    mutableCurrentPresent.value = PresentTypes.THIRD_PRESENT
                }
                else -> mutableCurrentPresent.value = PresentTypes.FOURTH_PRESENT
            }
        }
        mutableGameState.value = game.getGameState()
    }

    fun leftButtonClick() {
        game.appendLeftCombo()
        mutableGameState.value = game.getGameState()
    }

    fun rightButtonClick() {
        game.appendRightCombo()
        mutableGameState.value = game.getGameState()
    }

    fun downButtonClick() {
        game.appendDownCombo()
        mutableGameState.value = game.getGameState()
    }

    fun upButtonClick() {
        game.appendUpCombo()
        mutableGameState.value = game.getGameState()
    }
}