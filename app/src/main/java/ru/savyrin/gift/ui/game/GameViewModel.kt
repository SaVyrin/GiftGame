package ru.savyrin.gift.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val mutableGameState = MutableLiveData(GameState.NOT_STARTED)
    val gameState: LiveData<GameState> = mutableGameState

    private val game = Game()

    fun startGame() {
        game.start()
    }

    fun getPresentHealth(): Long {
        return game.getPresentHealth()
    }

    fun beatPresent() {
        game.beatPresent()
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